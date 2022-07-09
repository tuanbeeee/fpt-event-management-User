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
import sample.notification.NotificationDTO;
import sample.organization.OrganizationDTO;
import sample.users.UserDAO;

/**
 *
 * @author Tuan Be
 */
@WebServlet(name = "UserViewClubList", urlPatterns = {"/UserViewClubList"})
public class UserViewClubList extends HttpServlet {

    String SUCCSES = "User_View_Club_List.jsp";
    String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = ERROR;
        
        try {

            ArrayList<OrganizationDTO> list = new ArrayList<>();
            UserDAO getAllClub = new UserDAO();
            list = getAllClub.getAllOrganization();
            request.setAttribute("GET_ALL_CLUB", list);
            
            
            String user = request.getParameter("userID");
            if (user != null) {
                ArrayList<NotificationDTO> getNoti = new ArrayList<>();
                getNoti = getAllClub.getNotification(user);
                request.setAttribute("GET_NOTIFICATION", getNoti);
            }
            url = SUCCSES;
        
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
