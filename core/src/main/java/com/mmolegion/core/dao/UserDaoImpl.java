package com.mmolegion.core.dao;

import com.mmolegion.core.model.User;
import com.mmolegion.core.util.Time;
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

    @Override
    public void createUser(String username, String email, Map<String, String> hashedPassword) {
        logger.debug("Attempting to create user for username: " + username);

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordSalt(hashedPassword.get("salt"));
        user.setPasswordHash(hashedPassword.get("hash"));
        user.setAdmin(false);

        entityManager.persist(user);
    }

    @Override
    public int updateUser(User user) {
        logger.debug("Attempting to update user information for username: " + user.getUsername());

        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("email", user.getEmail());
        params.put("salt", user.getPasswordSalt());
        params.put("hash", user.getPasswordHash());

        String query = "update User u set u.username = :username, u.email = :email, u.passwordSalt = :salt, u.passwordHash = :hash where u.username = :username";
        return executeUpdate(entityManager, query, params);
    }

    @Override
    public int deleteUser(User user) {
        logger.debug("Attempting to delete user " + user.getUsername());

        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());

        String query = "delete from User u where u.username = :username";
        return executeUpdate(entityManager, query, params);
    }

    @Override
    public int incrementFailedAttempts(User user) {
        logger.debug("Incrementing number of failed attempts for user " + user.getUsername());

        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("failedAttempts", user.getFailedAttempts());

        String query = "update User u set u.failedAttempts = :failedAttempts where u.username = :username";
        return executeUpdate(entityManager, query, params);
    }

    @Override
    public int setLockout(User user) {
        logger.debug("Locking out user " + user.getUsername());

        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("failedAttempts", user.getFailedAttempts());
        params.put("lockoutUntil", Time.addMinutes(1).getTime());

        String query = "update User u set u.failedAttempts = :failedAttempts, u.lockoutUntil = :lockoutUntil where u.username = :username";
        return executeUpdate(entityManager, query, params);
    }

    @Override
    public int clearLockout(User user) {
        logger.debug("Clearing lockout and failed attempts for user " + user.getUsername());

        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("failedAttempts", 0);
        params.put("lockoutUntil", 0L);

        String query = "update User u set u.failedAttempts = :failedAttempts, u.lockoutUntil = :lockoutUntil where u.username = :username";
        return executeUpdate(entityManager, query, params);
    }

}
