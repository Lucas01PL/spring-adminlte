package com.hendisantika.adminlte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hendisantika.adminlte.model.Users;
import com.hendisantika.adminlte.repository.UsersRepository;
import com.hendisantika.adminlte.security.model.MyUserPrincipal;

@Service
public class MyUsersDetailsService implements UserDetailsService {
 
    @Autowired
    private UsersRepository userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }

}
