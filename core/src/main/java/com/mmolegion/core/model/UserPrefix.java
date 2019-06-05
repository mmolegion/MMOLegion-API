package com.mmolegion.core.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lgn_user_prefix")
public class UserPrefix implements Serializable {

    private static final long serialVersionUID = 2274757201141258120L;

    @Id
    @SequenceGenerator(name="lgn_user_prefix_prefixid_seq",
            sequenceName = "lgn_user_prefix_prefixid_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "lgn_user_prefix_prefixid_seq")
    @Column(name = "prefixId", updatable = false)
    private int prefixId;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "notes")
    private String notes;

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
    private User prefixUserCreatedByUser;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "modifiedByUserId")
    private User prefixUserModifiedByUser;

    public UserPrefix(String prefix, String notes, long createdDate, long modifiedDate, boolean isActive) {
        this.prefix = prefix;
        this.notes = notes;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isActive = isActive;
    }

    public UserPrefix() {
    }

    public int getPrefixId() {
        return prefixId;
    }

    public void setPrefixId(int prefixId) {
        this.prefixId = prefixId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public void setPrefixUserCreatedByUser(User prefixUserCreatedByUser) {
        this.prefixUserCreatedByUser = prefixUserCreatedByUser;
    }

    public void setPrefixUserModifiedByUser(User prefixUserModifiedByUser) {
        this.prefixUserModifiedByUser = prefixUserModifiedByUser;
    }
}
