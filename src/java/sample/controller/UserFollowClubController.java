/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.organization.OrganizationFollowerDTO;
import sample.users.UserDAO;

/**
 *
 * @author Tuan Be
 */
@WebServlet(name = "UserFollowClubController", urlPatterns = {"/UserFollowClubController"})
public class UserFollowClubController extends HttpServlet {

    private static final String SUCCESS = "UserViewClubDetail";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        try {

            String userID = request.getParameter("USER_ID");
            String orgID = request.getParameter("ORG_ID");

            OrganizationFollowerDTO userFollow = new OrganizationFollowerDTO();
            UserDAO dao = new UserDAO();

            userFollow = dao.getUserFollowing(userID, orgID);
            if (request.getParameter("FOLLOW") != null) {
                if (userFollow == null && dao.getUserUnfollow(userID, orgID) == null) {
                    dao.followByUser(userID, orgID);
                    url = SUCCESS;
                } else if (dao.getUserUnfollow(userID, orgID) != null) {
                    dao.followAgainByUser(userID, orgID);
                    url = SUCCESS;
                }
            } else if (request.getParameter("UNFOLLOW") != null) {
                if (userFollow != null) {
                    dao.unfollowByUser(userID, orgID);
                    url = SUCCESS;
                }
            }

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
