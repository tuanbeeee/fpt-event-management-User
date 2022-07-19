/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.blog.BlogDTO;
import sample.notification.NotificationDAO;
import sample.notification.NotificationDTO;
import sample.posts.BlogDAO;
import sample.posts.EventPost;
import sample.users.ParticipantsDTO;
import sample.users.UserDAO;

/**
 *
 * @author Tuan Be
 */
@WebServlet(name = "UserViewBlogDetailController", urlPatterns = {"/UserViewBlogDetailController"})
public class UserViewBlogDetailController extends HttpServlet {

    private static final String SUCCESS = "User_View_Blog_Detail.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {

            String blogID = request.getParameter("BLOG_ID");

            UserDAO dao = new UserDAO();
            NotificationDAO notiDAO = new NotificationDAO();
            BlogDAO blgDAO = new BlogDAO();

            int view = blgDAO.getBlogDetail(blogID).getNumberOfView() + 1;
            BlogDTO numberOfView = new BlogDTO(blogID, view);
            blgDAO.countBlogViewNumber(numberOfView);

            BlogDTO getBlogDetail = blgDAO.getBlogDetail(blogID);
            request.setAttribute("USER_VIEW_BLOG_DETAIL", getBlogDetail);
            
            String user = request.getParameter("userID");
            if (user != null) {
                ArrayList<NotificationDTO> getNoti = new ArrayList<>();
                getNoti = notiDAO.getNotification(user);
                request.setAttribute("GET_NOTIFICATION", getNoti);
            }

            url = SUCCESS;
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
