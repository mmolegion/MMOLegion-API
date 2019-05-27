package com.mmolegion.core.service;

import com.mmolegion.core.dao.UserDao;
import com.mmolegion.core.model.User;
import com.mmolegion.core.util.Crypto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUser(String username) {
        List<User> results = userDao.findUser(username);

        if(results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public User createUser(String username, String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user = findUser(username);

        if(user == null) {
            userDao.createUser(username, email, Crypto.generateHashedPassword(password));
            return findUser(username);
        }

        return null;
    }

    @Override
    @Transactional
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public int deleteUser(User user) {
        return userDao.deleteUser(user);
    }
}