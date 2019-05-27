package com.mmolegion.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> passwordSalt;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> passwordHash;
	public static volatile SingularAttribute<User, String> username;

	public static final String ID = "id";
	public static final String PASSWORD_SALT = "passwordSalt";
	public static final String EMAIL = "email";
	public static final String PASSWORD_HASH = "passwordHash";
	public static final String USERNAME = "username";

}

