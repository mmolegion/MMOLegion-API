package com.mmolegion.core.dao;

import com.mmolegion.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findAll();

    User findByUserId(int userId);

    <T extends User> List<T> saveAll(List<T> user);

    <T extends User> T save(T user);

    void deleteAll();

    void deleteByUserId(int userId);

}
