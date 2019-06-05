package com.mmolegion.core.dao;

import com.mmolegion.core.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDao extends JpaRepository<Purchase, Integer> {
}
