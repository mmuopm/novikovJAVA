package Proj.library.config.jwt;

import Proj.library.config.BCryptPasswordConfig;
import Proj.library.service.userdetails.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static Proj.dbexamples.constants.DBConstants.USER;
import static Proj.library.constants.SecurityConstants.RESOURCES_WHITE_LIST;
import static Proj.library.constants.SecurityConstants.USERS_REST_WHITE_LIST;
import static Proj.library.constants.UserRolesConstants.ADMIN;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JWTSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JWTTokenFilter jwtTokenFilter;

    public JWTSecurityConfig(BCryptPasswordConfig bCryptPasswordConfig,
                             CustomUserDetailsService customUserDetailsService,
                             JWTTokenFilter jwtTokenFilter) {
        this.customUserDetailsService=customUserDetailsService;
        this.jwtTokenFilter=jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain jwtSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(USERS_REST_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers("/authors/**").hasAnyRole(ADMIN, USER)
                        .anyRequest().authenticated()
                )
                .exceptionHandling()
                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(customUserDetailsService);
        return httpSecurity.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
