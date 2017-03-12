package com.educsystem.interfaces;

import com.educsystem.common.exceptions.UserDaoException;
import com.educsystem.database.pojo.User;

/**
 * Created by Denis on 12.03.2017.
 */
public interface UserDaoInf {
    public User getUser(String logindata, String passworddata) throws UserDaoException, ClassNotFoundException;
    public boolean regUser(String logindata, String email, String passworddata) throws ClassNotFoundException, UserDaoException;
}
