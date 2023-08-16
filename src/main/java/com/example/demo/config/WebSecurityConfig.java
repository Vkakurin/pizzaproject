package com.example.demo.config;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static com.example.demo.model.enums.Role.ADMIN;
import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAuthority;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // Подключаем WebSecurityConfigurerAdapter. Используется до версии Spring Boot 3. Нужно указать такую же версию в pom.xml
    @Autowired
    private UserService userService;

 //   @Autowired
//    public PasswordEncoder getPasswordEncoder;
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder(8);
//    }

    //Override - переопределяем методы из WebSecurityConfigurerAdapter
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/registration").permitAll()    // Указываем кто может пользоваться нашей системой без авторизации и указываем общедоступные страницы
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") // Указываем куда имеено будет перенаправляться запрос, если польщователь не авторизировался
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();                          // Строка, которая разрешает пост-запросы
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)//обект за авторизацию пользователя
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

}


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()//обект за авторизацию пользователя
//                .dataSource(dataSource)// обращение к БД
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .usersByUsernameQuery("select username, password, active from usr where username=?")
//                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.user_id = ur.user_id where u.username=?");
//    }



