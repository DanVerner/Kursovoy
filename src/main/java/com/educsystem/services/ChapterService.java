package com.educsystem.services;

import com.educsystem.common.exceptions.ChapterDaoException;
import com.educsystem.database.dao.ChapterDao;
import com.educsystem.database.pojo.Chapter;
import com.educsystem.interfaces.ChapterDaoInf;
import com.educsystem.interfaces.ChapterServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Denis on 26.02.2017.
 */
@Service
public class ChapterService implements ChapterServiceInf{
    private ChapterDaoInf chapterDao;

    @Autowired
    public ChapterService(ChapterDaoInf chapterDao){
        this.chapterDao = chapterDao;
    }

    public List<Chapter> getAllChapters() throws ClassNotFoundException, ChapterDaoException {
        return chapterDao.getAllChapters();
    }

    public boolean addChapter(String title, String description, int userlvl){
        return chapterDao.addChapter(title,description,userlvl);
    }
}
