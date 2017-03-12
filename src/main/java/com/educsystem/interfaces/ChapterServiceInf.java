package com.educsystem.interfaces;

import com.educsystem.common.exceptions.ChapterDaoException;
import com.educsystem.database.pojo.Chapter;

import java.util.List;

/**
 * Created by Denis on 12.03.2017.
 */
public interface ChapterServiceInf {
    public List<Chapter> getAllChapters() throws ClassNotFoundException, ChapterDaoException;
    public boolean addChapter(String title, String description, int userlvl);
}
