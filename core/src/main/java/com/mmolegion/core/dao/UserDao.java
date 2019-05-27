package com.mmolegion.core.dao;

import com.mmolegion.core.model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User> findUser(String username);

    void createUser(String username, String email, Map<String, String> generateHashedPassword);

    int updateUser(User user);

    int deleteUser(User user);

}
