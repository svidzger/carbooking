package hh.backend.carbooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import hh.backend.carbooking.web.UserDetailServiceImpl;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(toH2Console()).permitAll()
                .requestMatchers("/register", "/registeruser", "/", "/carlist", "/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().ignoringRequestMatchers(toH2Console())
                .and()
                .httpBasic()
                .and()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .defaultSuccessUrl("/carlist", true)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/carlist");
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

}