package com.mmolegion.core.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lgn_product_category")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 8686874316196090650L;

    @Id
    @SequenceGenerator(name="lgn_product_category_categoryid_seq",
            sequenceName = "lgn_product_category_categoryid_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "lgn_product_category_categoryid_seq")
    @Column(name = "categoryId", updatable = false)
    private int categoryId;

    @Column(name = "productType")
    private String productType;

    @Column(name = "description")
    private String description;

    @Column(name = "createdDate")
    private long createdDate;

    @Column(name = "modifiedDate")
    private long modifiedDate;

    @Column(name = "isActive")
    private boolean isActive;

    public ProductCategory(String productType, String description, long createdDate, long modifiedDate, boolean isActive) {
        this.productType = productType;
        this.description = description;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isActive = isActive;
    }

    public ProductCategory() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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
