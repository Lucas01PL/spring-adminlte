package com.hendisantika.adminlte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hendisantika.adminlte.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

    Users findByUsername(String username);
}
