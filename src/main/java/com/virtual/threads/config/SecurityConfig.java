package com.virtual.threads.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * package com.virtual.threads.config; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: BCryptPasswordEncoderConfig.java, v 0.1 2025-05-23 6:02 PM John Brix Pomoy Exp $$
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())   // updated usage
//                .httpBasic(Customizer.withDefaults());
        http
                .securityMatcher("/your-secured-path/**") // optional to limit security scope
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable); // disable CSRF explicitly using lambda
        return http.build();


    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoderConfig(){
        return new BCryptPasswordEncoder();
    }
}
