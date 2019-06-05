package com.mmolegion.core.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lgn_item_purchase")
public class ItemPurchase implements Serializable {

    private static final long serialVersionUID = 7840544574615161933L;

    @Id
    @SequenceGenerator(name="lgn_item_purchase_itempurchaseid_seq",
            sequenceName = "lgn_item_purchase_itempurchaseid_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "lgn_item_purchase_itempurchaseid_seq")
    @Column(name = "itemPurchaseId", updatable = false)
    private int itemPurchaseId;

    @Column(name = "price")
    private float price;

    @Column(name = "expires")
    private long expires;

    @Column(name = "isActive")
    private boolean isActive;

    public ItemPurchase(float price, long expires, boolean isActive) {
        this.price = price;
        this.expires = expires;
        this.isActive = isActive;
    }

    public ItemPurchase() {
    }

    public int getItemPurchaseId() {
        return itemPurchaseId;
    }

    public void setItemPurchaseId(int itemPurchaseId) {
        this.itemPurchaseId = itemPurchaseId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
