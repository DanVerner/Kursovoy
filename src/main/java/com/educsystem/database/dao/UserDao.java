package com.educsystem.database.dao;

import com.educsystem.common.exceptions.UserDaoException;
import com.educsystem.controllers.LoginController;
import com.educsystem.database.pojo.Competency;
import com.educsystem.database.pojo.User;
import com.educsystem.interfaces.UserDaoInf;
import org.apache.log4j.Logger;
import org.hibernate.Metamodel;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 23.02.2017.
 */
@Repository
public class UserDao implements UserDaoInf{
    private static final Logger log = Logger.getLogger(UserDao.class);
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("ISLEARN");
    private int userCompetency;
    private static List<String> recepList = new ArrayList<>();
//    private static final String SQL_FIND_USER =
//            "SELECT * FROM users WHERE users.username = ? AND users.password = ?;";
//    private static final String SQL_ADD_USER =
//            "INSERT INTO users(`username`,`email`,`password`,`level`,`role`) VALUES (?,?,?,?,?);";
//    private static final String SQL_ADMIN =
//            "SELECT email FROM users WHERE role = \"admin\"";
//    private static final String SQL_GET_USERLEVEL =
//            "SELECT level FROM users WHERE username = ?;";
//    private static final String SQL_GET_COMP =
//            "SELECT level,comp,lvl_id,competency FROM users u JOIN lvlproperties l ON u.level = l.lvl_id WHERE u.username = ?";
//    private static final String SQL_UPDATE_LEVEL =
//            "UPDATE users SET level = level + 1 WHERE username= ?";
//    private static final String SQL_UPDATE_COMPETENCY =
//            "UPDATE users SET comp = comp + 1 WHERE username = ?";

    private void getUserCompetency(String login){
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
        Root<User> user  = cq.from(User.class);
        cq.select(user.get("comp"));
        cq.where(cb.equal(user.get("username"),login));
        userCompetency = em.createQuery(cq).getSingleResult();
    }
    public boolean updateLevel(String login){
//        try(Connection conn = Pooler.getPoolConn();
//        PreparedStatement ps = conn.prepareStatement(SQL_GET_COMP);
//        PreparedStatement psu = conn.prepareStatement(SQL_UPDATE_LEVEL)){
//            int usercomp;
//            int levelcomp;
//            ps.setString(1,login);
//            psu.setString(1,login);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()) {
//                usercomp = rs.getInt("comp");
//                levelcomp = rs.getInt("competency");
//                if(usercomp >= levelcomp){
//                    int rsu = psu.executeUpdate();
//                    if (rsu != 0){
//                        log.trace("Level updated!");
//                        return true;
//                    } else {
//                        log.trace("Level update failed!");
//                    }
//                } else {
//                    log.trace("Not enough competency to update level!");
//                }
//            }
//        } catch (SQLException e) {
//            log.error(e);
//        } catch (NamingException e) {
//            log.error(e);
//        }
//        return false;
        getUserCompetency(login);
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
        Metamodel m = (Metamodel) em.getMetamodel();
        EntityType<User> compE = m.entity(User.class);
        Root<User> comp = cq.from(User.class);

//        cq.select(comp.get("competency"));
//        Join<User,Competency> competency = comp.join("level", JoinType.INNER );
//        competency.on(cb.equal(comp.get("level"), competency.get("lvl_id")));
//        cq.where(cb.equal(competency.get("username"),login));
//        int propComp = em.createQuery(cq).getSingleResult();
//
//        if(userCompetency >= propComp){
//            Root<User> userGetLevel = cq2.from(User.class);
//            cq2.select(userGetLevel.get("level"));
//            cq2.where(cb.equal(userGetLevel.get("username"), login));
//            int oldLevel = em.createQuery(cq2).getSingleResult();
//            int newLevel = oldLevel + 1;
//
//            Transaction tx = (Transaction) em.getTransaction();
//            tx.begin();
//            CriteriaUpdate<User> cu = cb.createCriteriaUpdate(User.class);
//            Root<User> userUpdLevel = cu.from(User.class);
//            cu.set(userUpdLevel.get("level"), newLevel);
//            cu.where(cb.equal(userUpdLevel.get("username"), login));
//            em.createQuery(cu).executeUpdate();
//            tx.commit();
//            return true;
//        }
//        return false;
        return true;
    }
    public boolean updateCompetency(String login){
//        try(Connection conn = Pooler.getPoolConn();
//        PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_COMPETENCY)){
//            ps.setString(1,login);
//            int rs = ps.executeUpdate();
//            if(rs != 0){
//                log.trace("Competency updated!");
//                return true;
//            } else {
//                log.trace("Competency update failed!");
//            }
//        } catch (SQLException e) {
//            log.error(e);
//        } catch (NamingException e) {
//            log.error(e);
//        }
//        return false;
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        getUserCompetency(login);
        int newCompetency = userCompetency + 1;

        Transaction tx = (Transaction) em.getTransaction();
        tx.begin();
        CriteriaUpdate<User> cu = cb.createCriteriaUpdate(User.class);
        Root<User> userUpdComp = cu.from(User.class);
        cu.set(userUpdComp.get("comp"), newCompetency);
        cu.where(cb.equal(userUpdComp.get("username"), login));
        em.createQuery(cu).executeUpdate();
        tx.commit();
        userCompetency = 0;
        return true;
    }

    public int getLevel(String login){
//        try(Connection conn = Pooler.getPoolConn();
//            PreparedStatement ps = conn.prepareStatement(SQL_GET_USERLEVEL)){
//            ps.setString(1, login);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                LoginController.userlevel = rs.getInt("level");
//            }
//            return LoginController.userlevel;
//        } catch (SQLException e) {
//            log.error(e);
//        } catch (NamingException e) {
//            log.error(e);
//        }
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
        Root<User> user = cq.from(User.class);
        cq.select(user.get("level"));
        cq.where(cb.equal(user.get("username"), login));
        LoginController.userlevel = em.createQuery(cq).getSingleResult();
        return LoginController.userlevel;
    }

    public User getUser(String logindata,String passworddata) throws UserDaoException, ClassNotFoundException {
//        log.debug("UserName: " + logindata + " Password: " + passworddata);
//        User user = null;
//
//        try (Connection conn = Pooler.getPoolConn();
//             PreparedStatement ps = conn.prepareStatement(SQL_FIND_USER)){
//            ps.setString(1, logindata);
//            ps.setString(2, passworddata);
//            ResultSet rs =  ps.executeQuery();
//            if(rs.next()){
//                log.debug("Searching...");
//                user = new User(
//                        rs.getInt("id"),
//                        rs.getString("username"),
//                        rs.getString("email"),
//                        rs.getString("password"),
//                        rs.getInt("level"),
//                        rs.getString("role"),
//                        rs.getInt("comp")
//                );
//                LoginController.userlevel = rs.getInt("level");
//                if(rs.getString("role").equals("admin")){
//                    try(PreparedStatement psa = conn.prepareStatement(SQL_ADMIN)){
//                        ResultSet rsa = psa.executeQuery();
//                        while(rsa.next()){
//                            recepList.add(rsa.getString("email"));
//                        }
//                        Mailer.send(logindata, recepList);
//                        recepList = null;
//                    } catch (AddressException e){
//                        log.error(e);
//                    } catch (SQLException e){
//                    log.error("Can't find admin!", e);
//                    }
//                }
//            } else {
//                log.debug("User with that credentials wasn't found!");
//            }
//        } catch (SQLException e){
//             log.error(e);
//             throw new UserDaoException();
//        } catch (NamingException e) {
//            log.error(e);
//        }
//        return user;
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("username"), logindata)
                )
        );
        List<User> users = em.createQuery(criteriaQuery).getResultList();
        return users.get(0);
    }

    public boolean regUser(String logindata, String email, String passworddata) throws ClassNotFoundException, UserDaoException {
//        try(Connection conn = Pooler.getPoolConn();
//            PreparedStatement ps = conn.prepareStatement(SQL_ADD_USER)){
//            ps.setString(1, logindata);
//            ps.setString(2, email);
//            ps.setString(3, passworddata);
//            ps.setInt(4, 1);
//            ps.setString(5,"ROLE_USER");
//            int update = ps.executeUpdate();
//            if (update > 0){
//                log.debug("New user added!");
//                return true;
//            } else {
//                log.debug("User wasn't added!");
//            }
//        } catch (SQLException e) {
//            log.error("Can't add user to DataBase!", e);
//            throw new UserDaoException();
//        } catch (NamingException e) {
//            log.error(e);
//        }
//        return false;
        EntityManager em = FACTORY.createEntityManager();
        Transaction tx = (Transaction) em.getTransaction();
        tx.begin();
        User user = new User();
        user.setUsername(logindata);
        user.setEmail(email);
        user.setPassword(passworddata);
        user.setRole("ROLE_USER");
        user.setLevel(1);
        user.setComp(0);
        em.persist(user);
        tx.commit();
        return true;
    }
}
