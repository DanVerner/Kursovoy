package com.educsystem.services;

import com.educsystem.common.exceptions.UserDaoException;
import com.educsystem.database.dao.UserDao;
import com.educsystem.interfaces.UserDaoInf;
import com.educsystem.interfaces.UserServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by Denis on 23.02.2017.
 */
@Service
public class UserService implements UserServiceInf{
    private UserDaoInf userDao;

    @Autowired
    public UserService(UserDaoInf userDao) {
        this.userDao = userDao;
    }

    public boolean authorize(String login, String password) throws UserDaoException, ClassNotFoundException, SQLException, NamingException {
        if(userDao.getUser(login, password) != null) return true;
        return false;
    }

    public boolean registration(String login, String email, String password) throws ClassNotFoundException, UserDaoException{
        return userDao.regUser(login, email, password);
    }

    public boolean getlevel(String login){
        if(userDao.getLevel(login) != 0) return true;
        return false;
    }

    public boolean updateCompetency(String login){
        return userDao.updateCompetency(login);
    }

    public boolean updateLevel(String login){
        return userDao.updateLevel(login);
    }
}
