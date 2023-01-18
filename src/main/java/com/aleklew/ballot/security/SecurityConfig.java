package com.aleklew.ballot.security;

import com.aleklew.ballot.modules.profiles.dbmodels.User;
import com.aleklew.ballot.modules.profiles.interfaces.UserRepository;
import com.aleklew.ballot.modules.profiles.services.UserProfileService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Optional;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserProfileService userProfileService;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(UserProfileService userProfileService,
                          JwtTokenFilter jwtTokenFilter) {
        this.userProfileService = userProfileService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();

        http.authorizeRequests()
                // Our public endpoints
                .antMatchers("/api/v1/public/**").permitAll()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**","/webjars/**","/swagger-resources/**", "/v2/**","/csrf")
                .permitAll()
                // Our private endpoints
                .antMatchers("/api/**").authenticated();

        // Add JWT token filter
        http.addFilterBefore(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            Optional<User> userDetails = Optional.ofNullable((User) userProfileService.loadUserByUsername(username));
            userDetails.orElseThrow(() -> new UsernameNotFoundException(
                    String.format("User: %s, not found", username)
            ));

            if (userDetails.isEmpty()) {
                throw new UsernameNotFoundException(
                        String.format("User: %s, not found", username)
                );
            }

            User user = userDetails.get();

            Collection<GrantedAuthority> authorityList = (Collection<GrantedAuthority>) user.getAuthorities();

            return user;
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {
                return null;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                boolean match = rawPassword.toString().equals(encodedPassword);
                return match;
            }

        };
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}