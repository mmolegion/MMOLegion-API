package com.mmolegion.core.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lgn_password")
public class Password implements Serializable {

    private static final long serialVersionUID = 5478581589425068650L;

    @Id
    @SequenceGenerator(name="lgn_user_password_passwordid_seq",
            sequenceName = "lgn_user_password_passwordid_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "lgn_user_password_passwordid_seq")
    @Column(name = "passwordId", updatable = false)
    private int passwordId;

    @Column(name = "passwordQuestion")
    private String passwordQuestion;

    @Column(name = "passwordAnswer")
    private String passwordAnswer;

    @Transient
    private String password;

    @Column(name = "passwordSalt")
    private String passwordSalt;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "createdDate")
    private long createdDate = new Date().getTime();

    @Column(name = "modifiedDate")
    private long modifiedDate = new Date().getTime();

    @Column(name = "isActive")
    private boolean isActive = true;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdByUserId")
    private User passwordUserCreatedByUser;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "modifiedByUserId")
    private User passwordUserModifiedByUser;

    public Password(String passwordQuestion, String passwordAnswer, String password, String passwordSalt, String passwordHash, long createdDate, long modifiedDate, boolean isActive) {
        this.passwordQuestion = passwordQuestion;
        this.passwordAnswer = passwordAnswer;
        this.password = password;
        this.passwordSalt = passwordSalt;
        this.passwordHash = passwordHash;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isActive = isActive;
    }

    public Password() {
    }

    public int getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(int passwordId) {
        this.passwordId = passwordId;
    }

    public String getPasswordQuestion() {
        return passwordQuestion;
    }

    public void setPasswordQuestion(String passwordQuestion) {
        this.passwordQuestion = passwordQuestion;
    }

    public String getPasswordAnswer() {
        return passwordAnswer;
    }

    public void setPasswordAnswer(String passwordAnswer) {
        this.passwordAnswer = passwordAnswer;
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

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setPasswordUserCreatedByUser(User userCreatedByUser) {
        this.passwordUserCreatedByUser = userCreatedByUser;
    }

    public void setPasswordUserModifiedByUser(User userModifiedByUser) {
        this.passwordUserModifiedByUser = userModifiedByUser;
    }
}
