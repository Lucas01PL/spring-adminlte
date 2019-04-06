package com.hendisantika.adminlte.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="users", schema="sys_users")
public class Users extends AbstractModel<Long> {
 
	private static final long serialVersionUID = -42763050027858397L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column(nullable=false, unique=true)
    private String username;
 
    @Column(nullable=false, unique=true)
    private String password;
}
