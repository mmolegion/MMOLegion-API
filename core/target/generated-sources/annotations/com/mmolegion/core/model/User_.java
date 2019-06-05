package com.mmolegion.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, User> passwordUserCreatedByUser;
	public static volatile SingularAttribute<User, User> createdByUser;
	public static volatile SingularAttribute<User, User> prefixUserCreatedByUser;
	public static volatile SingularAttribute<User, Boolean> isActive;
	public static volatile SingularAttribute<User, Integer> userId;
	public static volatile SingularAttribute<User, User> passwordUserModifiedByUser;
	public static volatile SingularAttribute<User, Password> password;
	public static volatile SingularAttribute<User, Long> createdDate;
	public static volatile SingularAttribute<User, User> userCreatedByUser;
	public static volatile SingularAttribute<User, User> prefixUserModifiedByUser;
	public static volatile SingularAttribute<User, User> userModifiedByUser;
	public static volatile SingularAttribute<User, User> modifiedByUser;
	public static volatile SingularAttribute<User, Long> modifiedDate;
	public static volatile SingularAttribute<User, UserPrefix> userPrefix;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> username;

	public static final String PASSWORD_USER_CREATED_BY_USER = "passwordUserCreatedByUser";
	public static final String CREATED_BY_USER = "createdByUser";
	public static final String PREFIX_USER_CREATED_BY_USER = "prefixUserCreatedByUser";
	public static final String IS_ACTIVE = "isActive";
	public static final String USER_ID = "userId";
	public static final String PASSWORD_USER_MODIFIED_BY_USER = "passwordUserModifiedByUser";
	public static final String PASSWORD = "password";
	public static final String CREATED_DATE = "createdDate";
	public static final String USER_CREATED_BY_USER = "userCreatedByUser";
	public static final String PREFIX_USER_MODIFIED_BY_USER = "prefixUserModifiedByUser";
	public static final String USER_MODIFIED_BY_USER = "userModifiedByUser";
	public static final String MODIFIED_BY_USER = "modifiedByUser";
	public static final String MODIFIED_DATE = "modifiedDate";
	public static final String USER_PREFIX = "userPrefix";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

