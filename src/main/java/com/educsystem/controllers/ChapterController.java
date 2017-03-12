package com.educsystem.controllers;

import com.educsystem.common.exceptions.ChapterDaoException;
import com.educsystem.database.pojo.Chapter;
import com.educsystem.services.ChapterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Denis on 07.03.2017.
 */
@Controller
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    private static final Logger log = Logger.getLogger(ChapterController.class);
    public static int ChID;

    @RequestMapping(value = "/chapters", method = RequestMethod.GET)
    public String getChaptersPage(Model model){
        if(LoginController.sessionID == null){
            return "redirect:login";
        } else {
            List<Chapter> chapterList = null;
            try {
                chapterList = chapterService.getAllChapters();
            } catch (ClassNotFoundException e) {
                log.error("Can't load chapters from DB", e);
            } catch (ChapterDaoException e){
                log.error(e);
            }
            model.addAttribute("chapterList", chapterList);
            return "index";
        }
    }

    @RequestMapping(value = "/chapters", method = RequestMethod.POST, params = {"chapconfirm"})
    public String getSelectedChapter(@RequestParam("selectChapter") String getChID){
        ChID = Integer.parseInt(getChID);
        return "redirect:chapters/lessons";
    }

    @RequestMapping(value = "/chapters", method = RequestMethod.POST, params = {"chapter_create"})
    public String getChapterAddPage(){
        return "redirect:chapters/add";
    }

    @RequestMapping(value = "/chapters/add", method = RequestMethod.GET)
    public String getChapterAdd(){
        if(LoginController.sessionID == null){
            return "redirect:login";
        } else {
            return "chapteradd";
        }
    }

    @RequestMapping(value = "/chapters/add", method = RequestMethod.POST)
    public String chapterAdd(@RequestParam("title") String title,
                             @RequestParam("description") String description,
                             @RequestParam("user_lvl") String strUserlvl,
                             Model model){
        int userlvl = Integer.parseInt(strUserlvl);
        if(chapterService.addChapter(title,description,userlvl)){
            log.trace("Chapter added");
            getChaptersPage(model);
            return "redirect:/chapters";
        } else {
            log.trace("Chapter adding failed!");
            model.addAttribute("chadd_fail","");
            return "redirect:chapteradd";
        }
    }
}
