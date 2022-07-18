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
import sample.users.ManagerDTO;
import sample.users.UserDAO;
import sample.users.UserDTO;

/**
 *
 * @author light
 */
@WebServlet(name = "UserDataController", urlPatterns = {"/UserDataController"})
public class UserDataController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String US_FORM = "User_Login_And_Register.jsp";
    private static final String US_PROFILE = "UserProfileController";
    private static final String US_PROFILE_CHANGE_PASS = "changePassword";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ArrayList<UserDTO> listType = new ArrayList<>();
        ArrayList<ManagerDTO> listOrg = new ArrayList<>();
        String getRequest = null;

        if (request.getParameter("request") == null) {
            getRequest = (String) request.getAttribute("request");
        } else {
            getRequest = request.getParameter("request");
        }

        UserDAO dao = new UserDAO();
        try {
            listType = dao.getAllUserType();
            request.setAttribute("listUserType", listType);

            String page = request.getParameter("page");
            
            if (US_FORM.equals(page)) {
                if (getRequest.equals("LOGIN")) {
                    request.setAttribute("request", getRequest);
                } else if (getRequest.equals("REGISTER")) {
                    request.setAttribute("request", getRequest);
                }
                url = US_FORM;

            } else if (page == null) {
                String login = "LOGIN";
                request.setAttribute("request", login);
                String message = (String) request.getAttribute("errMsg");
                request.setAttribute("errMsg", message);
                url = US_FORM;

            } else if (US_PROFILE.equals(page)) {
                url = US_PROFILE;
                request.setAttribute("edit", "Can Edit");

            } else if (US_PROFILE_CHANGE_PASS.equals(page)) {
                String confirmOldPass = (String) request.getAttribute("confirmOldPass");
                request.setAttribute("check", confirmOldPass);

                String correctOldPass = (String) request.getParameter("correctOldPass");
                request.setAttribute("correctOldPass", correctOldPass);

                url = US_PROFILE;
                request.setAttribute("edit", "Can Change Pass");

            }
        } catch (Exception e) {
            log("Error at UserDataController " + e.toString());
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
