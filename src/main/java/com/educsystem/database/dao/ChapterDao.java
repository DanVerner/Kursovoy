package com.educsystem.database.dao;

import com.educsystem.common.exceptions.ChapterDaoException;
import com.educsystem.controllers.LoginController;
import com.educsystem.database.connector.Pooler;
import com.educsystem.database.pojo.Chapter;
import com.educsystem.interfaces.ChapterDaoInf;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 26.02.2017.
 */
@Component
public class ChapterDao implements ChapterDaoInf{
    private static final Logger log = Logger.getLogger(ChapterDao.class);
    private static final String SQL_GET_CHAPTERS = "SELECT * FROM chapter WHERE user_level <= ?";
    private static String SQL_ADD_CHAPTERS = "INSERT INTO chapter(`title`,`description`,`user_level`) VALUES (?,?,?)";

    public List<Chapter> getAllChapters() throws ClassNotFoundException, ChapterDaoException {
        List<Chapter> chapterList = new ArrayList<>();
        try (Connection conn = Pooler.getPoolConn()){
            PreparedStatement st = conn.prepareStatement(SQL_GET_CHAPTERS);
//            Statement st = conn.createStatement();
            st.setInt(1, LoginController.userlevel);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Chapter chapter = new Chapter(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("user_level")
                );
                chapterList.add(chapter);
            }
        } catch (SQLException e){
            log.error("Can't execute query GET CHAPTERS", e);
            throw new ChapterDaoException();
        } catch (NamingException e) {
            log.error(e);
        }
        return chapterList;
    }

    public boolean addChapter(String title, String description, int userlvl){
        try(Connection conn = Pooler.getPoolConn();
            PreparedStatement ps = conn.prepareStatement(SQL_ADD_CHAPTERS)){
            ps.setString(1,title);
            ps.setString(2,description);
            ps.setInt(3,userlvl);
            int count = ps.executeUpdate();
            if(count > 0){
                log.debug("Chapter added");
                return true;
            } else {
                log.debug("Chapter not inserted!");
            }
        } catch (SQLException e) {
            log.error("Can't add chapter to DB", e);
        } catch (NamingException e) {
            log.error(e);
        }
        return false;
    }
}
