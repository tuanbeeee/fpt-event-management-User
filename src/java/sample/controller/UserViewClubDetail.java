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
import sample.notification.NotificationDAO;
import sample.notification.NotificationDTO;
import sample.organization.OrganizationDAO;
import sample.organization.OrganizationDTO;
import sample.organization.OrganizationFollowerDTO;
import sample.posts.EventDAO;
import sample.posts.EventPost;
import sample.users.UserDAO;

/**
 *
 * @author Tuan Be
 */
@WebServlet(name = "UserViewClubDetail", urlPatterns = {"/UserViewClubDetail"})
public class UserViewClubDetail extends HttpServlet {

    private static final String SUCCESS = "User_View_Club_Profile.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        try {

            String userID = request.getParameter("USER_ID");
            String orgID = request.getParameter("ORG_ID");
            String clubID = request.getParameter("CLUB_ID");

            UserDAO dao = new UserDAO();
            OrganizationDAO orgDAO = new OrganizationDAO();
            EventDAO evtDAO = new EventDAO();
            NotificationDAO notiDAO = new NotificationDAO();

            ArrayList<EventPost> list = new ArrayList<>();
            OrganizationDTO getClubInfo = new OrganizationDTO();
            OrganizationFollowerDTO userFollow = new OrganizationFollowerDTO();

            getClubInfo = orgDAO.getAClubInfo(clubID);
            list = evtDAO.getEventPostForClubProfile(clubID);

            userFollow = dao.getUserFollowing(userID, orgID);
            request.setAttribute("CHECK_USER_FOLLOWING", userFollow);
            request.setAttribute("CLB_INFO", getClubInfo);
            request.setAttribute("EVENT_POST", list);

            if (userID != null) {
                ArrayList<NotificationDTO> getNoti = new ArrayList<>();
                getNoti = notiDAO.getNotification(userID);
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
