package com.mmolegion.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductSubCategory.class)
public abstract class ProductSubCategory_ {

	public static volatile SingularAttribute<ProductSubCategory, String> subCategoryType;
	public static volatile SingularAttribute<ProductSubCategory, Long> createdDate;
	public static volatile SingularAttribute<ProductSubCategory, Long> modifiedDate;
	public static volatile SingularAttribute<ProductSubCategory, String> description;
	public static volatile SingularAttribute<ProductSubCategory, Boolean> isActive;
	public static volatile SingularAttribute<ProductSubCategory, Integer> categoryId;

	public static final String SUB_CATEGORY_TYPE = "subCategoryType";
	public static final String CREATED_DATE = "createdDate";
	public static final String MODIFIED_DATE = "modifiedDate";
	public static final String DESCRIPTION = "description";
	public static final String IS_ACTIVE = "isActive";
	public static final String CATEGORY_ID = "categoryId";

}

