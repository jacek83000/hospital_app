package com.example.hospital_app_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/doctors/**").hasAnyRole("ADMIN", "DOCTOR")
                .requestMatchers("/medications/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/patients/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/patients/**").hasAnyRole("ADMIN", "RECEPTIONIST")
                .requestMatchers(HttpMethod.PUT, "/patients/**").hasAnyRole("ADMIN", "RECEPTIONIST")
                .requestMatchers(HttpMethod.DELETE, "/patients/**").hasAnyRole("ADMIN", "RECEPTIONIST")
                .requestMatchers("/people/**").authenticated()
                .requestMatchers("/receipts/**").hasAnyRole("ADMIN", "DOCTOR")
                .requestMatchers(HttpMethod.GET, "/visits/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/visits/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/visits/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/visits/**").hasAnyRole("ADMIN", "RECEPTIONIST")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
