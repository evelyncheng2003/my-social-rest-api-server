package com.evelyn.myapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo_t")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="user_id")
    private Long userid;

    @Column(name="email")
    private String email;

    @Column(name="user_password")
    private String password;

    @Column(name="phone_number")
    private String phone_number;

    public User(Long userid, String email, String password, String phone_number) {
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }


    public Long getUserid() {
        return userid;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }
    public String getPhoneNumber() {
        return this.phone_number;
    }
}

