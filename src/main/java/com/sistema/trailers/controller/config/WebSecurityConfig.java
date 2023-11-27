package com.sistema.trailers.controller.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sistema.trailers.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
			.authorizeHttpRequests( (requests)->requests
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/admin").hasAnyAuthority("admin")
				.antMatchers("/home").hasAnyAuthority("admin", "usuario")
				.anyRequest().authenticated()
				)
				.formLogin((form) -> form
							.loginPage("/login")
							.usernameParameter("usuario")
							.passwordParameter("password")
							.defaultSuccessUrl("/home", true)
							.failureUrl("/login?error=true")
						)
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));
		return http.build();
	}
}

