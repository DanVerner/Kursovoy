package com.educsystem.controllers.Servlets;

import com.educsystem.common.exceptions.ChapterDaoException;
import com.educsystem.database.pojo.Chapter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.educsystem.services.ChapterService;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Denis on 26.02.2017.
 */
public class ChaptrServ extends HttpServlet {
    @Autowired
    private ChapterService chapterService;
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public static int ChID;

    private static Logger log = Logger.getLogger(ChaptrServ.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(LoginServ.sessionID != null) {
            List<Chapter> chapterList = null;
            try{
                chapterList = chapterService.getAllChapters();
            } catch (ClassNotFoundException e){
                log.error("Can't load chapters from DB", e);
            } catch (ChapterDaoException e){
                log.error(e);
            }
            req.setAttribute("chapterList", chapterList);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        } else {
            resp.sendRedirect("/kursovoy/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getChID = req.getParameter("selectChapter");
        ChID = Integer.parseInt(getChID);
        resp.sendRedirect("/kursovoy/chapters/lessons");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
