package com.mmolegion.core.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lgn_purchase")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 4704754146743014216L;

    @Id
    @SequenceGenerator(name="lgn_purchase_purchaseid_seq",
            sequenceName = "lgn_purchase_purchaseid_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "lgn_purchase_purchaseid_seq")
    @Column(name = "purchaseId", updatable = false)
    private int purchaseId;

    @Column(name = "requestIdentifier")
    private String requestIdentifier;

    public Purchase(String requestIdentifier) {
        this.requestIdentifier = requestIdentifier;
    }

    public Purchase() {
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getRequestIdentifier() {
        return requestIdentifier;
    }

    public void setRequestIdentifier(String requestIdentifier) {
        this.requestIdentifier = requestIdentifier;
    }

}
