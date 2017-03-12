package com.educsystem.controllers;

import com.educsystem.common.exceptions.LessonsDaoException;
import com.educsystem.database.pojo.Lessons;
import com.educsystem.services.LessonsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 07.03.2017.
 */
@Controller
public class LessonController {
    @Autowired
    private LessonsService lessonsService;

    public static int lesID;

    private static Logger log = Logger.getLogger(LessonController.class);

    @ExceptionHandler(value = Exception.class)
    public void met(){

    }

    @RequestMapping(value = "/chapters/lessons", method = RequestMethod.GET)
    public String getLessonPage(Model model){
        if(LoginController.sessionID == null){
            return "redirect:/login";
        } else {
            List<Lessons> lessonsList = null;
            try {
                lessonsList = lessonsService.getAllLessons(ChapterController.ChID);
            } catch (ClassNotFoundException e) {
                log.error("Can't load lessons from DB", e);
            } catch (LessonsDaoException e){
                log.error(e);
            }
            model.addAttribute("lessonsList", lessonsList);
            return "lessons";
        }
    }
    @RequestMapping(value = "/chapters/lessons", method = RequestMethod.POST, params = {"lessconfirm"})
    public String getLesson (@RequestParam ("selectLesson") String getLesId) throws LessonsDaoException, NamingException, SQLException, IOException {
        lesID = Integer.parseInt(getLesId);
        return "redirect:lessons/read";
    }

    @RequestMapping(value = "/chapters/lessons", method = RequestMethod.POST, params = {"lesson_create"})
    public String getLessonAddPage(){
        return "redirect:lessons/add";
    }

    @RequestMapping(value = "/chapters/lessons/add", method = RequestMethod.GET)
    public String getLessonAdd(){
        if(LoginController.sessionID == null){
            return "redirect:/login";
        } else {
            return "lessonadd";
        }
    }

    @RequestMapping(value = "/chapters/lessons/add", method = RequestMethod.POST)
    public String getLesson(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("path") String path,
                            Model model){
        if(lessonsService.addLesson(ChapterController.ChID,title,description,path)){
            log.trace("Lesson added");
            getLessonPage(model);
            return "redirect:/chapters/lessons";
        } else {
            log.trace("Lesson hasn't been added");
            model.addAttribute("lesadd_fail", "");
            return "redirect:/lessons/add";
        }
    }

}