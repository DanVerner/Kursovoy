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
public interface LessonsServiceInf {
    public List<Lessons> getAllLessons(int getChName) throws ClassNotFoundException, LessonsDaoException;
    public List<Lessons> getLesson(int lesID) throws LessonsDaoException, IOException, SQLException, NamingException;
    public boolean addLesson(int getChName, String title, String description, String path);
}
