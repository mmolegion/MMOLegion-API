package com.mmolegion.core.service;

import com.mmolegion.core.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> findAllUsers();

    User findUser(int userId);

    List<User> updateAllUsers(User user);

    User updateUser(User user);

    void deleteAllUsers();

    void deleteUser(int userId);

}
