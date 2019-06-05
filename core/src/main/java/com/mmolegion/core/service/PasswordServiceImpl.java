package com.mmolegion.core.service;

import com.mmolegion.core.dao.PasswordDao;
import com.mmolegion.core.model.Password;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordServiceImpl implements PasswordService {

    private static final Logger logger = LogManager.getLogger(PasswordServiceImpl.class);
    private final PasswordDao passwordDao;

    @Autowired
    public PasswordServiceImpl(PasswordDao passwordDao) {
        this.passwordDao = passwordDao;
    }

    @Override
    public List<Password> findAllPasswords() {
        return passwordDao.findAll();
    }

}
