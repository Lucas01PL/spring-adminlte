package com.hendisantika.adminlte.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hendisantika.adminlte.model.Users;
import com.hendisantika.adminlte.repository.UsersRepository;
import com.hendisantika.adminlte.security.model.MyUserPrincipal;
import com.hendisantika.adminlte.service.MyUsersDetailsService;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource datasource;
    
    @Autowired
    private MyUsersDetailsService userDetailsService;
    
    @Autowired
    private UsersRepository usersRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/bootstrap/**", "/dist/**", "/plugins/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .failureUrl("/login?error")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //Use Spring Boots User detailsMAnager
        //JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
        
        //Set our Datasource to use the one defined in application.properties
        //userDetailsService.setDataSource(datasource);
        
        //Create BCryptPassword encoder
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        //add components
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);

        // add new user "user" with password "password" - password will be encrypted
        UserDetails loadUserByUsername = null;
        try {
        	loadUserByUsername = userDetailsService.loadUserByUsername("naruto");
            if(loadUserByUsername != null) {
            	System.out.println("===> Usuário encontrado!!! Login: " + loadUserByUsername.getUsername());
            }
        } catch(Exception e){
        	System.out.println("===> Falha ao recuperar informacoes do usuario!!! Erro: " + e.getMessage());
        	Users u = new Users();
        	u.setUsername("naruto");
        	u.setPassword(encoder.encode("1234"));
        	usersRepository.save(u);
        	loadUserByUsername = new MyUserPrincipal(u);
        }
        
        if (loadUserByUsername != null) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            User userDetails = new User("naruto", encoder.encode("1234"), authorities);
        } 
    }

}
