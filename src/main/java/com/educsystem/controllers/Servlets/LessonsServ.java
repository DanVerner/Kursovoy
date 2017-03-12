package com.educsystem.controllers.Servlets;

import com.educsystem.common.exceptions.LessonsDaoException;
import com.educsystem.database.pojo.Lessons;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.educsystem.services.LessonsService;
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
public class LessonsServ extends HttpServlet {
    @Autowired
    private LessonsService lessonsService;
    public void setlessonsService(LessonsService lessonsService) {
        this.lessonsService = lessonsService;
    }

    private static Logger log = Logger.getLogger(LessonsServ.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (LoginServ.sessionID != null) {
            List<Lessons> lessonsList = null;
            try {
                lessonsList = lessonsService.getAllLessons(ChaptrServ.ChID);
            } catch (ClassNotFoundException e){
                log.error("Can't load lessons from DB", e);
            } catch (LessonsDaoException e){
                log.error(e);
            }
            req.setAttribute("lessonsList", lessonsList);
            req.getRequestDispatcher("/lessons.jsp").forward(req,resp);
        } else {
            resp.sendRedirect("/kursovoy/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
