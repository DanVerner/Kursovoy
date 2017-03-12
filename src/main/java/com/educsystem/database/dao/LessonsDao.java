package com.educsystem.database.dao;

import com.educsystem.common.exceptions.LessonsDaoException;
import com.educsystem.database.connector.Connector;
import com.educsystem.database.connector.Pooler;
import com.educsystem.database.pojo.Lessons;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 26.02.2017.
 */
@Component
public class LessonsDao {
    private static Logger log = Logger.getLogger(UserDao.class);
    private static String SQL_ALL_LESSONS = "SELECT * FROM lessons ls " +
                                            "JOIN chapter ch " +
                                            "ON ls.chapter_id=ch.id " +
                                            "WHERE ch.id=?";
    private static String SQL_GET_LESSON = "SELECT * FROM lessons WHERE id = ?";
    private static String SQL_ADD_LESSON = "INSERT INTO lessons(`chapter_id`,`title`,`description`,`path`) VALUES (?,?,?,?)";

    public List<Lessons> getAllLessons(int chName) throws ClassNotFoundException, LessonsDaoException {
        List<Lessons> lessonsList = new ArrayList<>();
        try (Connection conn = Pooler.getPoolConn()) {
            PreparedStatement st = conn.prepareStatement(SQL_ALL_LESSONS);
            st.setInt(1, chName);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Lessons lessons = new Lessons(
                        rs.getInt("id"),
                        rs.getInt("chapter_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("path")
                );
                lessonsList.add(lessons);
            }
        } catch (SQLException e){
            log.error("Can't execute query GET LESSONS!", e);
            throw new LessonsDaoException();
        } catch (NamingException e) {
            log.error(e);
        }
        return lessonsList;
    }

    public List<Lessons> getLesson(int lesID) throws LessonsDaoException, NamingException, SQLException, IOException{
        List<Lessons> lessonList = new ArrayList<>();
        try(Connection conn = Pooler.getPoolConn()) {
            PreparedStatement st = conn.prepareStatement(SQL_GET_LESSON);
            st.setInt(1,lesID);
            ResultSet rs = st.executeQuery();

            StringBuilder sb = new StringBuilder();
            String line;

            while (rs.next()){
                String path = rs.getString("path");
                BufferedReader reader = new BufferedReader(new FileReader(path));

                while ((line = reader.readLine()) != null){
                    sb.append(line + "\n");
                }
                String text = sb.toString();

                Lessons getLesson = new Lessons(
                        rs.getInt("id"),
                        rs.getInt("chapter_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        text
                );
                lessonList.add(getLesson);
            }
        }
        return lessonList;
    }

    public static boolean addLesson(int chapter_id, String title, String description, String path){
        try(Connection conn = Pooler.getPoolConn();
            PreparedStatement ps = conn.prepareStatement(SQL_ADD_LESSON)){
            ps.setInt(1,chapter_id);
            ps.setString(2,title);
            ps.setString(3,description);
            ps.setString(4,path);
            int count = ps.executeUpdate();
            if(count > 0){
                log.debug("Lesson has been added");
                return true;
            } else {
                log.debug("Lesson hasn't been added!");
            }
        } catch (SQLException e) {
            log.error("Can't add lesson to DB!", e);
        } catch (NamingException e) {
            log.error(e);
        }
        return false;
    }
}
