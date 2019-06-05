package com.mmolegion.core.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lgn_payment_method_provider")
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = -2545809067636289826L;

    @Id
    @SequenceGenerator(name="lgn_payment_method_provider_paymentmethodid_seq",
            sequenceName = "lgn_payment_method_provider_paymentmethodid_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "lgn_payment_method_provider_paymentmethodid_seq")
    @Column(name = "paymentMethodId", updatable = false)
    private int paymentMethodId;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "baseurl")
    private String baseurl;

    @Column(name = "description")
    private String description;

    @Column(name = "createdDate")
    private long createdDate;

    @Column(name = "modifiedDate")
    private long modifiedDate;

    @Column(name = "isActive")
    private boolean isActive;

    public PaymentMethod(String companyName, String baseurl, String description, long createdDate, long modifiedDate, boolean isActive) {
        this.companyName = companyName;
        this.baseurl = baseurl;
        this.description = description;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isActive = isActive;
    }

    public PaymentMethod() {
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
