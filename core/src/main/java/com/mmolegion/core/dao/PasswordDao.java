package com.mmolegion.core.dao;

import com.mmolegion.core.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordDao extends JpaRepository<Password, Integer> {

    List<Password> findAll();
}
