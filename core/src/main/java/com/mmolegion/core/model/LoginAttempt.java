package com.mmolegion.core.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lgn_login_attempt")
public class LoginAttempt implements Serializable {

    private static final long serialVersionUID = -4459119903766966166L;

    @Id
    @SequenceGenerator(name="lgn_login_attempt_attemptid_seq",
            sequenceName = "lgn_login_attempt_attemptid_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "lgn_login_attempt_attemptid_seq")
    @Column(name = "attemptId", updatable = false)
    private int attemptId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "ipAddress")
    private String ipAddress;

    @Column(name = "browserType")
    private String browserType;

    @Column(name = "success")
    private boolean success;

    @Column(name = "createdDate")
    private long createdDate;

    public LoginAttempt(String username, String email, String ipAddress, String browserType, boolean success, long createdDate) {
        this.username = username;
        this.email = email;
        this.ipAddress = ipAddress;
        this.browserType = browserType;
        this.success = success;
        this.createdDate = createdDate;
    }

    public LoginAttempt() {
    }

    public int getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(int attemptId) {
        this.attemptId = attemptId;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }
}
