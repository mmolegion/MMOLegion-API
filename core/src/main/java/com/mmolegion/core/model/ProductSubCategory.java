package com.mmolegion.core.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lgn_product_subcategory")
public class ProductSubCategory implements Serializable {

    private static final long serialVersionUID = -1154107693462312872L;

    @Id
    @SequenceGenerator(name="lgn_product_subcategory_subcategoryid_seq",
            sequenceName = "lgn_product_subcategory_subcategoryid_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "lgn_product_subcategory_subcategoryid_seq")
    @Column(name = "subCategoryId", updatable = false)
    private int categoryId;

    @Column(name = "subCategoryType")
    private String subCategoryType;

    @Column(name = "description")
    private String description;

    @Column(name = "createdDate")
    private long createdDate;

    @Column(name = "modifiedDate")
    private long modifiedDate;

    @Column(name = "isActive")
    private boolean isActive;

    public ProductSubCategory(String subCategoryType, String description, long createdDate, long modifiedDate, boolean isActive) {
        this.subCategoryType = subCategoryType;
        this.description = description;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isActive = isActive;
    }

    public ProductSubCategory() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryType() {
        return subCategoryType;
    }

    public void setSubCategoryType(String subCategoryType) {
        this.subCategoryType = subCategoryType;
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
