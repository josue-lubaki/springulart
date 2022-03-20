package ca.ghostteam.springulart.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
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
    private final SecretKey secretKey;

    @Autowired
    public JwtTokenVerifier(JwtConfig jwtConfig, SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Retrieve Authorization: Bearer yt3sg4hsu4...
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

        if(StringUtils.hasText(authorizationHeader)
                && authorizationHeader.startsWith(jwtConfig.getTokenPrefix())){
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(authorizationHeader.replace(jwtConfig.getTokenPrefix(), ""))
                        .getBody();

                // Retrieve user information
                String username = claims.getSubject();
                List<Map<String, String>> authorities = (List<Map<String, String>>) claims.get("authorities");
                Set<SimpleGrantedAuthority> roles = authorities.stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
                        .collect(Collectors.toSet());

                // Create Authentication object
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        roles);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }   catch (ExpiredJwtException e) {
                // Token expired
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
            }
        }

        filterChain.doFilter(request, response);
    }
}