package com.educsystem.controllers;

import com.educsystem.common.exceptions.ChapterDaoException;
import com.educsystem.database.pojo.Chapter;
import com.educsystem.interfaces.ChapterServiceInf;
import com.educsystem.interfaces.UserServiceInf;
import com.educsystem.services.ChapterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by Denis on 07.03.2017.
 */
@Controller
public class ChapterController {
    private ChapterServiceInf chapterService;
    private UserServiceInf userService;

    @Autowired
    public ChapterController(ChapterServiceInf chapterService,  UserServiceInf userService) {
        this.chapterService = chapterService;
        this.userService = userService;
    }

    private static final Logger log = Logger.getLogger(ChapterController.class);
    public static int ChID;

    @RequestMapping(value = "/chapters", method = RequestMethod.GET)
    public String getChaptersPage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(userService.getlevel(name)) {
            List<Chapter> chapterList = null;
            try {
                chapterList = chapterService.getAllChapters();
            } catch (ClassNotFoundException e) {
                log.error("Can't load chapters from DB", e);
            } catch (ChapterDaoException e) {
                log.error(e);
            }
            model.addAttribute("chapterList", chapterList);
            return "index";
        } else {
            return "redirect:error";
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
        return "chapteradd";
    }

    @RequestMapping(value = "/chapters/add", method = RequestMethod.POST)
    public String chapterAdd(@RequestParam Map<String,String> requestParams,
                             Model model){
        String title = requestParams.get("title");
        String description = requestParams.get("description");
        String strUserlvl = requestParams.get("user_lvl");
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
