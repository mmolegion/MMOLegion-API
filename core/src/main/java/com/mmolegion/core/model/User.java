package com.mmolegion.core.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_account")
public class User implements Serializable {

    private static final long serialVersionUID = 8696470445722242385L;

    @Id
    @SequenceGenerator(name="user_account_seq",
            sequenceName = "user_account_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "user_account_seq")
    @Column(name = "id", updatable = false)
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

    @Column(name = "isAdmin")
    private boolean isAdmin;

    @Column(name = "failedAttempts")
    private int failedAttempts;

    @Column(name = "lockoutUntil")
    private long lockoutUntil;

    public User(int id, String username, String email, String passwordSalt, String passwordHash, boolean isAdmin, int failedAttempts, long lockoutUntil) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordSalt = passwordSalt;
        this.passwordHash = passwordHash;
        this.isAdmin = isAdmin;
        this.failedAttempts = failedAttempts;
        this.lockoutUntil = lockoutUntil;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int attempts) {
        this.failedAttempts = attempts;
    }

    public long getLockoutUntil() {
        return lockoutUntil;
    }

    public void setLockoutUntil(long lockout) {
        this.lockoutUntil = lockout;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", isAdmin=" + isAdmin +
                ", failedAttempts=" + failedAttempts +
                ", lockoutUntil=" + lockoutUntil +
                '}';
    }
}
