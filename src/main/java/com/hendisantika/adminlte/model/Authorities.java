package com.hendisantika.adminlte.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
public class Authorities {

	private String authority;
	
	@ManyToOne
	@JoinColumn(name="users_id", referencedColumnName="id")
	private Users user;
	
}
