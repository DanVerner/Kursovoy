package com.educsystem.services;

import com.educsystem.common.exceptions.UserDaoException;
import com.educsystem.database.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by Denis on 23.02.2017.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean authorize(String login, String password) throws UserDaoException, ClassNotFoundException, SQLException, NamingException {
        if(userDao.getUser(login, password) != null) return true;
        return false;
    }

    public boolean registration(String login, String email, String password) throws ClassNotFoundException, UserDaoException{
        return userDao.regUser(login, email, password);
    }
}