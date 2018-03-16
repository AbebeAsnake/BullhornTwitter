package com.abebe.demo.sec;

import com.abebe.demo.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfiguration   extends WebSecurityConfigurerAdapter{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired

AppUserRepository userRepository;
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUDS(userRepository);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder pE = passwordEncoder();
        auth.inMemoryAuthentication().withUser("username").password(pE.encode("password")).authorities("USER")
                .and().withUser("admin").password(pE.encode("password")).authorities("ADMIN");
        auth.userDetailsService(userDetailsServiceBean());
    }

    private static final String[] PUBLIC_MATCHERS = {

            "/h2-console/**",
            "/register",
            "/search",
            "/css/**",
            "/templates/**",
            "/js/**",
            "/login"

    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antmatchers: if you have a route you want to block off
//                .permitall: dont need access pages everyone one can acees this route example:register
                .antMatchers(PUBLIC_MATCHERS).permitAll()
//                .access("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
                .antMatchers("/admin", "/searchterm", "/").access("hasAuthority('USER') or hasAuthority('ADMIN')" )
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll().permitAll()
                .and()
                .httpBasic();


        http
                .csrf()
                .disable();
        http
                .headers().frameOptions().disable();
    }
//.httpBasic() This means that the user can avoid a login prompt by putting his/her login details in the request.
//    This can be used for testing, but should be removed before the application goes live.

//            configure() This overrides the default configure method, configures users who can access the application. By
//    default, Spring Boot will provide a new random password assigned to the user "user" when it starts up, if you
//do not include this method.
//
//    Once you include this method, you will be able to log in with the users configured here. At this point, the
//    configuration is for a single in-memory user. Multiple users can be configured here, as you wi\\ see when you
//    remove the comments in the additional code. This is also the method in which you can configure how users are granted access to the appliaction if their details are stored in a database.





}
