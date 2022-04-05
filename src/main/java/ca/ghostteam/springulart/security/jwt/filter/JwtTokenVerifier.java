package ca.ghostteam.springulart.security.jwt.filter;

import ca.ghostteam.springulart.config.bean.JwtConfig;
import ca.ghostteam.springulart.service.user.UserService;
import ca.ghostteam.springulart.dto.UserDetailsDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@Component
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final UserService userDetailsService;

    @Autowired
    public JwtTokenVerifier(JwtConfig jwtConfig,
                            UserService userDetailsService) {
        this.jwtConfig = jwtConfig;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // if the request is for login or register, skip the verification
        if(request.getRequestURI().matches("/auth/login")
                || request.getRequestURI().matches("/auth/register")
                || request.getRequestURI().matches("/api/v1/haircuts")){
            filterChain.doFilter(request, response);
            return;
        }

        // retrieve Authorization :  Bearer yt3sg4su7...
        // extract token
        String token = extractJwtToken(request);

        if(token != null) {
            try {
                DecodedJWT decodeJWTToken = decodeJWT(token, jwtConfig.getSecretKey());

                // Decode a token
                String username = decodeJWTToken.getSubject();
                UserDetailsDTO userDetails = (UserDetailsDTO) userDetailsService.loadUserByUsername(username);

                // retrieve claims "authorities" from payload of token
                List<Map> authorities = decodeJWTToken.getClaims().get("authorities").asList(Map.class);
                Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.get("authority").toString()))
                        .collect(Collectors.toSet());

                // create UsernamePasswordAuthenticationToken and give it the permissions
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getCredentials(),
                        grantedAuthorities
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // set authentication on context Application
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Method to decode the token
     * @param token to decode
     * @return DecodeJWT
     * */
    public DecodedJWT decodeJWT(String token, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(jwtConfig.getApplicationName())
                .build();
        return verifier.verify(token);
    }

    /**
     * Method to extract the token
     * @param request HttpServletRequest where retrieve token
     * @return String || null
     * */
    public String extractJwtToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        if(StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(jwtConfig.getTokenPrefix())){
            return authorizationHeader.replace(jwtConfig.getTokenPrefix(), "").trim();
        }
        return null;
    }
}