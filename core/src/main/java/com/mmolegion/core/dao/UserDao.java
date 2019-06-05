package com.mmolegion.core.dao;

import com.mmolegion.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    @Query("select u, up from User u left join u.userPrefix up")
    List<User> findAll();

    User getUserByUsername(User user);

    User getUserByEmail(User user);

    <T extends User> T save(T user);

    void delete(User user);
}
