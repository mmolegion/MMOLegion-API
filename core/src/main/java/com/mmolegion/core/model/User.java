package com.mmolegion.core.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 8696470445722242385L;

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Transient
    private String password;

    @Column(name = "passwordSalt")
    private String passwordSalt;

    @Column(name = "passwordHash")
    private String passwordHash;

    public User(int id, String username, String email, String passwordSalt, String passwordHash) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordSalt = passwordSalt;
        this.passwordHash = passwordHash;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String password_salt) {
        this.passwordSalt = password_salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password_hash) {
        this.passwordHash = password_hash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
