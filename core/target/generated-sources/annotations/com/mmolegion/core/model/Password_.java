package com.mmolegion.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Password.class)
public abstract class Password_ {

	public static volatile SingularAttribute<Password, User> passwordUserCreatedByUser;
	public static volatile SingularAttribute<Password, String> passwordAnswer;
	public static volatile SingularAttribute<Password, Integer> passwordId;
	public static volatile SingularAttribute<Password, Long> createdDate;
	public static volatile SingularAttribute<Password, Long> modifiedDate;
	public static volatile SingularAttribute<Password, String> passwordQuestion;
	public static volatile SingularAttribute<Password, Boolean> isActive;
	public static volatile SingularAttribute<Password, String> passwordSalt;
	public static volatile SingularAttribute<Password, User> user;
	public static volatile SingularAttribute<Password, User> passwordUserModifiedByUser;
	public static volatile SingularAttribute<Password, String> passwordHash;

	public static final String PASSWORD_USER_CREATED_BY_USER = "passwordUserCreatedByUser";
	public static final String PASSWORD_ANSWER = "passwordAnswer";
	public static final String PASSWORD_ID = "passwordId";
	public static final String CREATED_DATE = "createdDate";
	public static final String MODIFIED_DATE = "modifiedDate";
	public static final String PASSWORD_QUESTION = "passwordQuestion";
	public static final String IS_ACTIVE = "isActive";
	public static final String PASSWORD_SALT = "passwordSalt";
	public static final String USER = "user";
	public static final String PASSWORD_USER_MODIFIED_BY_USER = "passwordUserModifiedByUser";
	public static final String PASSWORD_HASH = "passwordHash";

}

