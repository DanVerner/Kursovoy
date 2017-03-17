package com.educsystem.controllers;

import com.educsystem.common.exceptions.LessonsDaoException;
import com.educsystem.database.pojo.Lessons;
import com.educsystem.interfaces.LessonsServiceInf;
import com.educsystem.services.LessonsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 08.03.2017.
 */
@Controller
public class ListController {
    private LessonsServiceInf lessonsService;

    @Autowired
    public ListController(LessonsServiceInf lessonsService) {
        this.lessonsService = lessonsService;
    }

    private static Logger log = Logger.getLogger(ListController.class);

    @RequestMapping(value = "/chapters/lessons/read", method = RequestMethod.GET)
    public String getListPage(Model model) throws Exception{
        List<Lessons> lessonList = null;
        lessonList = lessonsService.getLesson(LessonController.lesID);
        model.addAttribute("lessonList",lessonList);
        return "list";
    }

    @RequestMapping(value = "/chapters/lessons/read", method = RequestMethod.POST)
    public String getBack(){
        return "redirect:/chapters/lessons";
    }
}
