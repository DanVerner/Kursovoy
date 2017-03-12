package com.educsystem.interfaces;

import com.educsystem.common.exceptions.LessonsDaoException;
import com.educsystem.database.pojo.Lessons;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 12.03.2017.
 */
public interface LessonsDaoInf {
    public List<Lessons> getAllLessons(int chName) throws ClassNotFoundException, LessonsDaoException;
    public List<Lessons> getLesson(int lesID) throws LessonsDaoException, NamingException, SQLException, IOException;
    public boolean addLesson(int chapter_id, String title, String description, String path);
}
