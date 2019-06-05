package com.mmolegion.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemPurchase.class)
public abstract class ItemPurchase_ {

	public static volatile SingularAttribute<ItemPurchase, Long> expires;
	public static volatile SingularAttribute<ItemPurchase, Integer> itemPurchaseId;
	public static volatile SingularAttribute<ItemPurchase, Float> price;
	public static volatile SingularAttribute<ItemPurchase, Boolean> isActive;

	public static final String EXPIRES = "expires";
	public static final String ITEM_PURCHASE_ID = "itemPurchaseId";
	public static final String PRICE = "price";
	public static final String IS_ACTIVE = "isActive";

}

