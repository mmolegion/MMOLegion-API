package com.mmolegion.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserPrefix.class)
public abstract class UserPrefix_ {

	public static volatile SingularAttribute<UserPrefix, String> notes;
	public static volatile SingularAttribute<UserPrefix, Long> createdDate;
	public static volatile SingularAttribute<UserPrefix, User> prefixUserModifiedByUser;
	public static volatile SingularAttribute<UserPrefix, User> prefixUserCreatedByUser;
	public static volatile SingularAttribute<UserPrefix, String> prefix;
	public static volatile SingularAttribute<UserPrefix, Long> modifiedDate;
	public static volatile SingularAttribute<UserPrefix, Boolean> isActive;
	public static volatile SingularAttribute<UserPrefix, User> user;
	public static volatile SingularAttribute<UserPrefix, Integer> prefixId;

	public static final String NOTES = "notes";
	public static final String CREATED_DATE = "createdDate";
	public static final String PREFIX_USER_MODIFIED_BY_USER = "prefixUserModifiedByUser";
	public static final String PREFIX_USER_CREATED_BY_USER = "prefixUserCreatedByUser";
	public static final String PREFIX = "prefix";
	public static final String MODIFIED_DATE = "modifiedDate";
	public static final String IS_ACTIVE = "isActive";
	public static final String USER = "user";
	public static final String PREFIX_ID = "prefixId";

}

