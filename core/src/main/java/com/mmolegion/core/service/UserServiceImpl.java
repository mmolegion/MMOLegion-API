package com.mmolegion.core.service;

import com.mmolegion.core.dao.UserDao;
import com.mmolegion.core.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUser(String username, String password) {
        List<User> results = userDao.findUser(username);

        if(results.size() > 0)
            return results.get(0);
        else
            return null;
    }
}
