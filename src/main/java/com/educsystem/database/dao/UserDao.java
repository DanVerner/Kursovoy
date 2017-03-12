package com.educsystem.database.dao;

import com.educsystem.common.exceptions.UserDaoException;
import com.educsystem.common.mail.Mailer;
import com.educsystem.controllers.Servlets.LoginServ;
import com.educsystem.database.connector.Pooler;
import com.educsystem.database.pojo.User;
import com.educsystem.interfaces.UserDaoInf;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.mail.internet.AddressException;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 23.02.2017.
 */
@Component
public class UserDao implements UserDaoInf{
    private static final Logger log = Logger.getLogger(UserDao.class);

    private static final String SQL_FIND_USER =
            "SELECT * FROM users WHERE users.username = ? AND users.password = ?;";
    private static final String SQL_ADD_USER =
            "INSERT INTO users(`username`,`email`,`password`,`level`,`role`) VALUES (?,?,?,?,?);";
    private static final String SQL_ADMIN =
            "SELECT email FROM users WHERE role = \"admin\"";
    private static final String SQL_IS_ADMIN =
            "SELECT role FROM users WHERE email = ?";
    private static List<String> recepList = new ArrayList<>();

    public User getUser(String logindata, String passworddata) throws UserDaoException, ClassNotFoundException {
        log.debug("UserName: " + logindata + " Password: " + passworddata);
        User user = null;

        try (Connection conn = Pooler.getPoolConn();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_USER)){
            ps.setString(1, logindata);
            ps.setString(2, passworddata);
            ResultSet rs =  ps.executeQuery();
            if(rs.next()){
                log.debug("Searching...");
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("level"),
                        rs.getString("role")
                );
                LoginServ.userlevel = rs.getInt("level");
                if(rs.getString("role").equals("admin")){
                    try(PreparedStatement psa = conn.prepareStatement(SQL_ADMIN)){
                        ResultSet rsa = psa.executeQuery();
                        while(rsa.next()){
                            recepList.add(rsa.getString("email"));
                        }
                        Mailer.send(logindata, recepList);
                        recepList = null;
                    } catch (AddressException e){
                        log.error(e);
                    } catch (SQLException e){
                    log.error("Can't find admin!", e);
                    }
                }
            } else {
                log.debug("User with that credentials wasn't found!");
            }
        } catch (SQLException e){
             log.error(e);
             throw new UserDaoException();
        } catch (NamingException e) {
            log.error(e);
        }
        return user;
    }

    public boolean regUser(String logindata, String email, String passworddata) throws ClassNotFoundException, UserDaoException {
        try(Connection conn = Pooler.getPoolConn();
            PreparedStatement ps = conn.prepareStatement(SQL_ADD_USER)){
            ps.setString(1, logindata);
            ps.setString(2, email);
            ps.setString(3, passworddata);
            ps.setInt(4, 1);
            ps.setString(5,"user");
            int update = ps.executeUpdate();
            if (update > 0){
                log.debug("New user added!");
                return true;
            } else {
                log.debug("User wasn't added!");
            }
        } catch (SQLException e) {
            log.error("Can't add user to DataBase!", e);
            throw new UserDaoException();
        } catch (NamingException e) {
            log.error(e);
        }
        return false;
    }

    public boolean isAdmin(String email) throws ClassNotFoundException{
        try(Connection conn = Pooler.getPoolConn();
            PreparedStatement ps = conn.prepareStatement(SQL_IS_ADMIN)){

        } catch (SQLException e){
            log.trace("User is not admin! Access Denied!");
        } catch (NamingException e) {
            log.error(e);
        }
        return false;
    }
}
