package com.projectmanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .and()
                .withUser("hayes").password(passwordEncoder().encode("hayes")).roles("ADMIN")
                .and()
                .withUser("david").password(passwordEncoder().encode("david")).roles("ADMIN")
                .and()
                .withUser("gianni").password(passwordEncoder().encode("gianni")).roles("ADMIN")
                .and()
                .withUser("kasey").password(passwordEncoder().encode("kasey")).roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("dashboard");
    }
}
