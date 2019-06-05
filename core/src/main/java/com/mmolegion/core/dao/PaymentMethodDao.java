package com.mmolegion.core.dao;

import com.mmolegion.core.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodDao extends JpaRepository<PaymentMethod, Integer> {
}
