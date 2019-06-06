package com.mmolegion.core.service;

import com.mmolegion.core.dao.UserDao;
import com.mmolegion.core.model.ItemPurchase;
import com.mmolegion.core.model.Purchase;
import com.mmolegion.core.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public User findUser(int userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public List<User> updateAllUsers(User user) {
        List<User>  users = findAllUsers();

        return userDao.saveAll(users);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteAllUsers() {
        userDao.deleteAll();
    }

    @Override
    @Transactional
    public void deleteUser(int userId) {
        userDao.deleteByUserId(userId);
    }

}
