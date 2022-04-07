package ca.ghostteam.springulart.security.jwt.filter;

import ca.ghostteam.springulart.config.bean.JwtConfig;
import ca.ghostteam.springulart.dto.UserDetailsDTO;
import ca.ghostteam.springulart.service.user.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@Component
@EnableWebMvc
@Slf4j
public class JwtTokenVerifier extends OncePerRequestFilter implements WebMvcConfigurer {

    private final JwtConfig jwtConfig;
    private final UserService userDetailsService;

    @Autowired
    public JwtTokenVerifier(JwtConfig jwtConfig,
                            UserService userDetailsService) {
        this.jwtConfig = jwtConfig;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // retrieve Authorization :  Bearer yt3sg4su7...
        // extract token
        String token = extractJwtToken(request);
        configureRequestAndResponseForCors(request, response, token);

        // check any other method other than OPTIONS
        if (!(request.getMethod().equalsIgnoreCase("OPTIONS")))
//                && ((request.getMethod().equalsIgnoreCase("POST"))
//                || (request.getMethod().equalsIgnoreCase("DELETE"))
//                || (request.getMethod().equalsIgnoreCase("PUT"))
//                || (request.getMethod().equalsIgnoreCase("GET"))))
              {

            // if the request is for login or register, skip the verification
            // regex not verify /auth and /api/v1/haircuts and /api/v1/reservations
            Pattern pattern = Pattern.compile("^(/auth|/api/v1/haircuts|/api/v1/users|/api/v1/reservations).*$");

            if((pattern.matcher(request.getRequestURI()).matches() && request.getMethod().equals("GET"))
                    || (request.getRequestURI().matches("^(/auth)") && request.getMethod().equals("POST"))){
                filterChain.doFilter(request, response);
                return;
            }


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
        else {
            System.out.println("Pre-flight");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers"+"Authorization, content-type," +
                    "USERID"+"ROLE"+
                    "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Method to configure the request and response for CORS
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * */
    private void configureRequestAndResponseForCors(HttpServletRequest request, HttpServletResponse response, String token) {
        System.out.println("WebConfig; " + request.getRequestURI());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization," +
                "X-Requested-With,observe,ResponseType");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "Authorization Bearer " + token);
        response.addHeader("Access-Control-Expose-Headers", "responseType");
        response.addHeader("Access-Control-Expose-Headers", "observe");
        System.out.println("Request Method: " + request.getMethod());
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