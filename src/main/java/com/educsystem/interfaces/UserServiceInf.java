package com.educsystem.interfaces;

import com.educsystem.common.exceptions.UserDaoException;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by Denis on 12.03.2017.
 */
public interface UserServiceInf {
    boolean authorize(String login, String password) throws UserDaoException, ClassNotFoundException, SQLException, NamingException;
    boolean registration(String login, String email, String password) throws ClassNotFoundException, UserDaoException;
}
