package com.mmolegion.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PaymentMethod.class)
public abstract class PaymentMethod_ {

	public static volatile SingularAttribute<PaymentMethod, String> baseurl;
	public static volatile SingularAttribute<PaymentMethod, Long> createdDate;
	public static volatile SingularAttribute<PaymentMethod, Integer> paymentMethodId;
	public static volatile SingularAttribute<PaymentMethod, String> companyName;
	public static volatile SingularAttribute<PaymentMethod, Long> modifiedDate;
	public static volatile SingularAttribute<PaymentMethod, String> description;
	public static volatile SingularAttribute<PaymentMethod, Boolean> isActive;

	public static final String BASEURL = "baseurl";
	public static final String CREATED_DATE = "createdDate";
	public static final String PAYMENT_METHOD_ID = "paymentMethodId";
	public static final String COMPANY_NAME = "companyName";
	public static final String MODIFIED_DATE = "modifiedDate";
	public static final String DESCRIPTION = "description";
	public static final String IS_ACTIVE = "isActive";

}

