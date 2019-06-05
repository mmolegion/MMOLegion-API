package com.mmolegion.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LoginAttempt.class)
public abstract class LoginAttempt_ {

	public static volatile SingularAttribute<LoginAttempt, Long> createdDate;
	public static volatile SingularAttribute<LoginAttempt, Boolean> success;
	public static volatile SingularAttribute<LoginAttempt, String> ipAddress;
	public static volatile SingularAttribute<LoginAttempt, String> email;
	public static volatile SingularAttribute<LoginAttempt, Integer> attemptId;
	public static volatile SingularAttribute<LoginAttempt, String> username;
	public static volatile SingularAttribute<LoginAttempt, String> browserType;

	public static final String CREATED_DATE = "createdDate";
	public static final String SUCCESS = "success";
	public static final String IP_ADDRESS = "ipAddress";
	public static final String EMAIL = "email";
	public static final String ATTEMPT_ID = "attemptId";
	public static final String USERNAME = "username";
	public static final String BROWSER_TYPE = "browserType";

}

