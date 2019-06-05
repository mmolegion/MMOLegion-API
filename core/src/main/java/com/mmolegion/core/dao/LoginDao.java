package com.mmolegion.core.dao;

import com.mmolegion.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends JpaRepository<User, Integer> {
}
