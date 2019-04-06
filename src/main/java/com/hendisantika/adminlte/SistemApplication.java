package com.hendisantika.adminlte;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class SistemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemApplication.class, args);
	}

	/*
	@Bean(name="dataSource")
	 public DriverManagerDataSource dataSource() {
	     DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	     driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
	     driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/");
	     driverManagerDataSource.setSchema("sys_users");
	     driverManagerDataSource.setUsername("postgres");
	     driverManagerDataSource.setPassword("admin");
	     return driverManagerDataSource;

	 }
	*/
}
