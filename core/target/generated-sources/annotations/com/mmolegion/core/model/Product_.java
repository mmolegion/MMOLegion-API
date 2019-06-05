package com.mmolegion.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Long> createdDate;
	public static volatile SingularAttribute<Product, Integer> productId;
	public static volatile SingularAttribute<Product, Float> price;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Long> modifiedDate;
	public static volatile SingularAttribute<Product, String> description;
	public static volatile SingularAttribute<Product, Boolean> isActive;

	public static final String CREATED_DATE = "createdDate";
	public static final String PRODUCT_ID = "productId";
	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String MODIFIED_DATE = "modifiedDate";
	public static final String DESCRIPTION = "description";
	public static final String IS_ACTIVE = "isActive";

}

