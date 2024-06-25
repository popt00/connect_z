package ca.parimal.connectz.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests (
                configurer -> configurer
                        .requestMatchers("/currentuser/*").hasRole("USER")
                        .requestMatchers("/admin/*").hasRole("ADMIN")
                        .requestMatchers("signup").permitAll()
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> formLogin.loginPage("/login")
                        .loginProcessingUrl("/authenticate")
                        .permitAll()
        ).logout(
                logout -> logout.logoutUrl("/logout")
                        .permitAll()
        ).exceptionHandling(
                configurer -> configurer.accessDeniedPage("/access-denied")
        );
        return http.build();
    }
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) throws Exception {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, active from users where username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select users.username, roles.role from roles " +
                " inner join users on users.user_id = roles.user_id" +
                " where username=?");
        return jdbcUserDetailsManager;
    }
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
}
