package com.mmolegion.core.dao;

import com.mmolegion.core.model.User;

import java.util.List;

public interface UserDao {

    List<User> findUser(String username);

}
