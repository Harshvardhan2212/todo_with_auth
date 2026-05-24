package com.example.todo_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((authorize) -> {
          // authorize.requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN");
          // authorize.requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN");
          // authorize.requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN");
          // authorize.requestMatchers(HttpMethod.PATCH, "/**").hasAnyRole("ADMIN",
          // "USER");
          authorize.requestMatchers(HttpMethod.GET, "/**").permitAll();
          authorize.anyRequest().authenticated();
        })
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
    return authenticationConfiguration.getAuthenticationManager();
  }

  // @Bean
  // public UserDetailsService userDetailsService() {
  // UserDetails harsh = User.builder()
  // .username("harsh")
  // .password("password").build();
  //
  // UserDetails admin = User.builder()
  // .username("admin")
  // .password("password")
  // .roles("ADMIN").build();
  // return new InMemoryUserDetailsManager(harsh, admin);
  // }

}
