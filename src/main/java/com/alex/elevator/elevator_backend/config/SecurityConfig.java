//package com.alex.elevator.elevator_backend.config;
//
//import com.dm.game_manager_backend.login.CustomOAuth2UserService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Value("${spring.security.user.name:}")
//    private String username;
//
//    @Value("${spring.security.user.password:")
//    private String password;
//
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    private final com.dm.game_manager_backend.user.UserDetailsService userDetailsService;
//
//    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, com.dm.game_manager_backend.user.UserDetailsService userDetailsService) {
//        this.customOAuth2UserService = customOAuth2UserService;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf ->
//                        csrf.ignoringRequestMatchers(
//                                "/api/registration/","/api/log-entry/add"
//                        )
//                )
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/api/log-entry/add").permitAll()
//                                .requestMatchers("/api/").authenticated()
//                                .requestMatchers("/login", "/css/", "/js/","/images/", "/webjars/").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/login")
//                                .permitAll()
//                )
//                .logout(logout ->
//                        logout.permitAll()
//                )
//                .oauth2Login(oauth2Login ->
//                        oauth2Login
//                                .loginPage("/login")
//                                .defaultSuccessUrl("/")
//                                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService))
//                )
//                .httpBasic(httpBasic ->
//                        httpBasic.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                );
//        return http.build();
//    }
//}
