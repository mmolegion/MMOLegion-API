package com.mmolegion.core.dao;

import com.mmolegion.core.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends GenericDao implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findUser(String username) {
        logger.debug("Attempting to find valid user for username: " + username);

        Map<String, Object> params = new HashMap<>();
        params.put("username", username);

        String query = "select u from User u where u.username = :username";

        return executeQuery(entityManager, query, params, User.class);
    }

}
