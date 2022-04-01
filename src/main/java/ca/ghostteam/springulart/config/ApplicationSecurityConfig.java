package ca.ghostteam.springulart.config;

import ca.ghostteam.springulart.config.bean.JwtConfig;
import ca.ghostteam.springulart.security.ApplicationUserRole;
import ca.ghostteam.springulart.security.jwt.JwtAuthenticationEntryPoint;
import ca.ghostteam.springulart.security.jwt.filter.JwtTokenVerifier;
import ca.ghostteam.springulart.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_ENDPOINTS = {
            "/",
            "/index",
            "/auth/login",
            "/auth/register"
    };

    private static final String[] PUBLIC_ENDPOINTS_GET_METHOD = {
            "/api/v1/haircuts",
            "/api/v1/haircuts/**"
    };

    private static final String[] SWAGGER_ENDPOINTS = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"};

    private static final String[] CLIENT_BARBER_ENDPOINTS = {
            "/api/v1/users/**",
            "/api/v1/haircuts/**",
            "/api/v1/reservations/**"
    };

    private static final String[] ADMIN_ENDPOINTS = {
            "/management/api/v1/users/**",
            "/management/api/v1/haircuts/**",
            "/management/api/v1/reservations/**"
    };

    private final UserService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationEntryPoint jwtEntryPoint;
    private final UserService userService;
    private final JwtConfig jwtConfig;

    public ApplicationSecurityConfig(UserService userDetailsService,
                                     PasswordEncoder passwordEncoder,
                                     JwtAuthenticationEntryPoint jwtEntryPoint,
                                     UserService userService,
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
                .authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                // permitAll /api/v1/haircuts/** en mode GET
                .antMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS_GET_METHOD).permitAll()
                .antMatchers(CLIENT_BARBER_ENDPOINTS).hasAnyRole(
                        ApplicationUserRole.CLIENT.name(),
                        ApplicationUserRole.BARBER.name()
                )
                .antMatchers(ADMIN_ENDPOINTS).hasRole(ApplicationUserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new JwtTokenVerifier(jwtConfig,userDetailsService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(SWAGGER_ENDPOINTS);
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

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
