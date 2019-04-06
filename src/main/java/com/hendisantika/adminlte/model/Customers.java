package com.hendisantika.adminlte.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;

@Data
@Entity
@Table(name="customers", schema="sys_users")
public class Customers extends AbstractModel<Long> {

    @Column(nullable = false, length = 40)
    private String firstname;

    @Column(nullable = false, length = 40)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "added_date", insertable = false, updatable = false)
    private Date addedDate;

}
