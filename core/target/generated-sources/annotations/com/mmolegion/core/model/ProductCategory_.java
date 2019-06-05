package com.mmolegion.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductCategory.class)
public abstract class ProductCategory_ {

	public static volatile SingularAttribute<ProductCategory, Long> createdDate;
	public static volatile SingularAttribute<ProductCategory, Long> modifiedDate;
	public static volatile SingularAttribute<ProductCategory, String> description;
	public static volatile SingularAttribute<ProductCategory, Boolean> isActive;
	public static volatile SingularAttribute<ProductCategory, Integer> categoryId;
	public static volatile SingularAttribute<ProductCategory, String> productType;

	public static final String CREATED_DATE = "createdDate";
	public static final String MODIFIED_DATE = "modifiedDate";
	public static final String DESCRIPTION = "description";
	public static final String IS_ACTIVE = "isActive";
	public static final String CATEGORY_ID = "categoryId";
	public static final String PRODUCT_TYPE = "productType";

}

