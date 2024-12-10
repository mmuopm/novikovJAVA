package Proj.library.config;

import Proj.library.service.userdetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

import static Proj.library.constants.UserRolesConstants.ADMIN;
import static Proj.library.constants.UserRolesConstants.LIBRARIAN;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.customUserDetailsService=customUserDetailsService;
    }

    private final List<String> RESOURCES_WHITE_LIST = List.of(
            "/resources/**",
            "/static/**",
            "/js/**",
            "/css/**",
            "/",
            "swagger-iu/**"
    );

    private final List<String> BOOKS_WHITE_LIST = List.of("/books");
    private final List<String> BOOKS_PERMISIONS_LIST = List.of(
            "/books/add",
            "/books/update",
            "/books/delete"
    );

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(BOOKS_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(BOOKS_PERMISIONS_LIST.toArray(String[]::new)).hasAnyRole(ADMIN, LIBRARIAN)
                        .anyRequest().authenticated()
                )
                //настройка для входа в систему
                .formLogin((form)->form
                        .loginPage("/login")
                        //перенапрвление на главную страницу после успеха
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout)->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                );
        return httpSecurity.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}