package kr.co.zzimcar.config;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  DataSource dataSource;

//  @Autowired
//  UsersService usersService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    log.info("security config..............");

    http.csrf().disable();

//    http.authorizeRequests().antMatchers("/guest/**").permitAll();
//    http.authorizeRequests().antMatchers("/week/**").permitAll();
//    http.authorizeRequests().antMatchers("/task/**").permitAll();
//    http.authorizeRequests().antMatchers("/").hasAnyRole("CEO","CTO","실장","본부장","책임","팀장","과장","선임","연구원","매니저");
//    http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
//    http.authorizeRequests()
//      .antMatchers(HttpMethod.OPTIONS,"/oauth/token").permitAll();
//
//    http.formLogin().loginPage("/login");
//    http.exceptionHandling().accessDeniedPage("/accessDenied");
//    http.logout().logoutUrl("/logout").invalidateHttpSession(true);

//    http.rememberMe()
//        .key("zerock")
//        .userDetailsService(usersService)
//        .tokenRepository(getJDBCRepository())
//      .tokenValiditySeconds(60 * 60 * 24);
  }
//  private PersistentTokenRepository getJDBCRepository() {
//    JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
//    repo.setDataSource(dataSource);
//    return repo;
//  }
//
//  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    log.info("build Auth global........");
//    auth.userDetailsService(usersService).passwordEncoder(passwordEncoder());
//  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Override
  public void configure(WebSecurity web) {
    String[] swaggerPath = new String[]{"/v2/api-docs", "/swagger-resources/**",
      "/", "/swagger-ui.html", "/webjars/**", "/swagger/**", "/csrf", "/favicon.ico"};

    web.ignoring()
      .antMatchers("/exception/**", "/kakao/login", "/kakao/code", "/license/return", "/test/**", "/nice-id", "/nice-id/**", "/billgate/pay",
        "/billgate/pay-return", "/billgate/test", "/batch/active-user","/task/**","/memberAPI/**");
  }
}