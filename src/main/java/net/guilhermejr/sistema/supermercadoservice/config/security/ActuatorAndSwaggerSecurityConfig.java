package net.guilhermejr.sistema.supermercadoservice.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class ActuatorAndSwaggerSecurityConfig {

    @Value("${spring.boot.admin.client.instance.metadata.user.name}")
    private String actuatorLogin;

    @Value("${spring.boot.admin.client.instance.metadata.user.password}")
    private String actuatorSenha;

    @Value("${sistema.swagger.login}")
    private String swaggerLogin;

    @Value("${sistema.swagger.senha}")
    private String swaggerSenha;

    private final PasswordEncoder passwordEncoder;

    public ActuatorAndSwaggerSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChainActuatorAndSwagger(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**").hasRole("ACTUATOR")
                .antMatchers("/swagger-ui/**", "/v3/**").hasRole("SWAGGER")
                .and()
                .csrf().disable()
                .formLogin();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsActuatorAndSwaggerService() {

        UserDetails actuator = User
                .builder()
                .username(actuatorLogin)
                .password(passwordEncoder.encode(actuatorSenha))
                .roles("ACTUATOR")
                .build();

        UserDetails swagger = User
                .builder()
                .username(swaggerLogin)
                .password(passwordEncoder.encode(swaggerSenha))
                .roles("SWAGGER")
                .build();

        return new InMemoryUserDetailsManager(actuator, swagger);

    }

}
