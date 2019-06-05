package com.mmolegion.core.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lgn_user")
public class User implements Serializable {

    private static final long serialVersionUID = 8696470445722242385L;

    @Id
    @SequenceGenerator(name="lgn_user_userid_seq",
            sequenceName = "lgn_user_userid_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "lgn_user_userid_seq")
    @Column(name = "userId", updatable = false)
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "createdDate")
    private long createdDate = new Date().getTime();

    @Column(name = "modifiedDate")
    private long modifiedDate = new Date().getTime();

    @Column(name = "isActive")
    private boolean isActive = true;

    // For User.class
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdByUserId")
    private User createdByUser;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "modifiedByUserId")
    private User modifiedByUser;

    @OneToOne(mappedBy = "createdByUser", cascade = CascadeType.ALL)
    private User userCreatedByUser;

    @OneToOne(mappedBy = "modifiedByUser", cascade = CascadeType.ALL)
    private User userModifiedByUser;

    // For UserPrefix.class
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserPrefix userPrefix;

    @OneToOne(mappedBy = "createdByUser", cascade = CascadeType.ALL)
    private User prefixUserCreatedByUser;

    @OneToOne(mappedBy = "modifiedByUser", cascade = CascadeType.ALL)
    private User prefixUserModifiedByUser;

    // For Password.class
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Password password;

    @OneToOne(mappedBy = "createdByUser", cascade = CascadeType.ALL)
    private User passwordUserCreatedByUser;

    @OneToOne(mappedBy = "modifiedByUser", cascade = CascadeType.ALL)
    private User passwordUserModifiedByUser;


    public User(String username, String email, long createdDate, long modifiedDate, boolean isActive) {
        this.username = username;
        this.email = email;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isActive = isActive;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public void setModifiedByUser(User modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public void setUserPrefix(UserPrefix userPrefix) {
        this.userPrefix = userPrefix;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}
