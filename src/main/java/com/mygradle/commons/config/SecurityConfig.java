package com.mygradle.commons.config;

import com.mygradle.commons.security.MemberAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/resources/**",
                        "/h2-console/**", "/webjars/**", "/signup", "/home", "/").permitAll()
                .anyRequest().authenticated()
                .and()
                //iframe
                .headers()
                .frameOptions().disable()
                .and()
                //DB admin
                .csrf()
                .ignoringAntMatchers("/h2-console/**")
                .and()
                //login
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/sign-in")
                .defaultSuccessUrl("/welcome")
                .failureUrl("/login?error")
                .usernameParameter("email")
                .passwordParameter("passwd")
                .permitAll()
                .and()
                //Exception Handler
                .exceptionHandling().accessDeniedHandler(memberAccessDeniedHandler())
                .and()
                .rememberMe()
                .key("jpub")
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("jpubcookie")
                .tokenValiditySeconds(86400) //1day
                .tokenRepository(rememberMeTokenService()).userDetailsService(myUserService())
                .and()
                //logout
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserService()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public FilterRegistrationBean<Filter> getSpringSecurityFilterChainBindedToError(@Qualifier("springSecurityFilterChain") Filter springSecurityFilterChain){
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(springSecurityFilterChain);
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MyUserService myUserService(){
        return new MyUserService();
    }

    @Bean
    public RememberMeTokenService rememberMeTokenService() throws Exception{
        return new RememberMeTokenService();
    }

    @Bean
    public MemberAccessDeniedHandler memberAccessDeniedHandler() throws Exception{
        return new MemberAccessDeniedHandler();
    }
}
