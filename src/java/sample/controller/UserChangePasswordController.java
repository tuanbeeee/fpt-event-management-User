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
import javax.servlet.http.HttpSession;
import sample.users.UserDAO;
import sample.users.UserDTO;
import sample.users.UserError;

/**
 *
 * @author Tuan Be
 */
@WebServlet(name = "UserChangePasswordController", urlPatterns = {"/UserChangePasswordController"})
public class UserChangePasswordController extends HttpServlet {

    private static final String SUCCESS = "UserProfileController";
    private static final String ERROR = "UserDataController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        boolean check = true;
        UserDAO dao = new UserDAO();
        UserDTO dto = new UserDTO();
        UserError error = new UserError();
        try {
            String oldPass = request.getParameter("oldPass");
            if (oldPass == null) {
                oldPass = request.getParameter("oldPassWhenChange");
            }
            String newPass = request.getParameter("newPass");
            String confirmNewPass = request.getParameter("confirmNewPass");
            String userID = request.getParameter("userID");
            String oldPassWhenChange = request.getParameter("oldPassWhenChange");

            String checkPass = dao.authenticateUser(userID, oldPass);
            if (checkPass.equals("Invalid user !")) {
                error.setOldPasswordError("Old password is not correct !");
                request.setAttribute("ERROR", error);
                request.setAttribute("confirmOldPass", "wrong");
                url = ERROR;
            } else if (!checkPass.equals("Invalid user !")) {
                request.setAttribute("confirmOldPass", "correct");
                request.setAttribute("getCorrectOldPass", oldPass);
                url = ERROR;
                if (oldPassWhenChange != null) {
                    if (newPass.equals(dao.getPass(userID))) {
                        error.setPasswordError("The new password must not be the same as the old password !");
                        check = false;
                    }

                    if (!confirmNewPass.equals(newPass)) {
                        error.setPasswordConfirmError("Wrong confirm password !");
                        check = false;
                    }
                    if (check == true) {
                        dao.changePassword(userID, newPass);
                        dto = dao.checkUserExist(userID);
                        session.setAttribute("LOGIN_USER", dto);
                        request.setAttribute("Message", "CHANGE PASSWORD SUCCESSFULLY!");
                        url = SUCCESS;
                    } else {
                        String page = request.getParameter("page");
                        request.setAttribute("edit", "Can Change Pass");
                        request.setAttribute("ERROR", error);
                        url = ERROR;
                    }
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
