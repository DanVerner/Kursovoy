package com.educsystem.services;

import com.educsystem.common.exceptions.LessonsDaoException;
import com.educsystem.database.dao.LessonsDao;
import com.educsystem.database.pojo.Lessons;
import com.educsystem.interfaces.LessonsDaoInf;
import com.educsystem.interfaces.LessonsServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 26.02.2017.
 */
@Service
public class LessonsService implements LessonsServiceInf{
    private LessonsDaoInf lessonsDao;

    @Autowired
    public LessonsService(LessonsDaoInf lessonsDao) {
        this.lessonsDao = lessonsDao;
    }

    public List<Lessons> getAllLessons(int getChName) throws ClassNotFoundException, LessonsDaoException {
        return lessonsDao.getAllLessons(getChName);
    }

    public List<Lessons> getLesson(int lesID) throws LessonsDaoException, IOException, SQLException, NamingException {
        return lessonsDao.getLesson(lesID);
    }

    public boolean addLesson(int getChName, String title, String description, String path){
        return lessonsDao.addLesson(getChName, title, description, path);
    }
}
