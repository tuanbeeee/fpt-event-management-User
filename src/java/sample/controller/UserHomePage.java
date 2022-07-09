/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.blog.BlogDTO;
import sample.notification.NotificationDTO;
import sample.posts.EventPost;
import sample.users.UserDAO;
import sample.users.UserDTO;

/**
 *
 * @author Tuan Be
 */
@WebServlet(name = "UserHomePage", urlPatterns = {"/UserHomePage"})
public class UserHomePage extends HttpServlet {

    public static final String SUCCES = "User_Homepage.jsp";
    public static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            UserDAO dao = new UserDAO();

            ArrayList<EventPost> getEventNextDate = new ArrayList<>();
            getEventNextDate = dao.getEventByDate(dateFormat.format(dao.getTomorow()));
            String tomorrow = dateFormat.format(dao.getTomorow());
            request.setAttribute("EVENT_TOMOROW", getEventNextDate);
            request.setAttribute("GET_TOMORROW", tomorrow);

            ArrayList<EventPost> getEventTheDayAfterTomorrow = new ArrayList<>();
            getEventTheDayAfterTomorrow = dao.getEventByDate(dateFormat.format(dao.getTheDayAfterTomorow()));
            String theDayAfterTomorrow = dateFormat.format(dao.getTheDayAfterTomorow());
            request.setAttribute("EVENT_THE_DAY_AFTER_TOMORROW", getEventTheDayAfterTomorrow);
            request.setAttribute("GET_THE_DAY_AFTER_TOMORROW", theDayAfterTomorrow);

            ArrayList<EventPost> getImgCLB = new ArrayList<>();
            getImgCLB = dao.getInfoForHomePage();
            request.setAttribute("IMG_CLB", getImgCLB);

            ArrayList<EventPost> getNewestEvent = new ArrayList<>();
            getNewestEvent = dao.getNewestEvent();
            request.setAttribute("GET_NEWEST_EVENT", getNewestEvent);

            ArrayList<BlogDTO> getNewestBlog = new ArrayList<>();
            getNewestBlog = dao.getNewestBlog();
            request.setAttribute("GET_NEWEST_BLOG", getNewestBlog);

            UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
            if (user != null) {
                ArrayList<NotificationDTO> getNoti = new ArrayList<>();
                getNoti = dao.getNotification(user.getId());
                request.setAttribute("GET_NOTIFICATION", getNoti);
            }

            url = SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
