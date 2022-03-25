package ca.ghostteam.springulart.security;

import ca.ghostteam.springulart.service.UserService;
import ca.ghostteam.springulart.security.jwt.JwtAuthenticationEntryPoint;
import ca.ghostteam.springulart.bean.JwtConfig;
import ca.ghostteam.springulart.security.jwt.filter.JwtTokenVerifier;
import ca.ghostteam.springulart.security.jwt.filter.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            // -- other swagger related endpoints
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            // -- allow anonymous resource requests
            "/",
            "index",
            "/login",
            "/register",
            "/logout",
            "/error",
            "/favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/css/*",
            "/js/*"
    };

    private final UserService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationEntryPoint jwtEntryPoint;
    private final UserService userService;
    private final JwtConfig jwtConfig;

    public ApplicationSecurityConfig(UserService userDetailsService,
                                     PasswordEncoder passwordEncoder,
                                     JwtAuthenticationEntryPoint jwtEntryPoint,
                                     @Qualifier("user-service-fake") UserService userService,
                                     JwtConfig jwtConfig
                                     ) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtEntryPoint = jwtEntryPoint;
        this.userService = userService;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig, userService))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig,userDetailsService), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/api/**").hasAnyRole(
                        ApplicationUserRole.CLIENT.name(),
                        ApplicationUserRole.BARBER.name()
                )
                .antMatchers("management/api/**").hasRole(ApplicationUserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/api/v1/users", true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
