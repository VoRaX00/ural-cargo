package ural.cargo.ru.config;

import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import ural.auth.ru.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final JwtAuthEntryPoint jwtAuthEntryPoint;

    private final String[] allowedUrls = {
        "/swagger-ui/**",
        "/v3/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(c -> {})
            .csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(c -> c
                .authenticationEntryPoint(jwtAuthEntryPoint))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(c -> c
                .requestMatchers(allowedUrls).permitAll()
                .anyRequest().authenticated())
            .sessionManagement(c -> c
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }

}
