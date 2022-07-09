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
@WebServlet(name = "UpdateProfileController", urlPatterns = {"/UpdateProfileController"})
public class UpdateProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            String userID = request.getParameter("uid");
            String userName = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            UserDTO checkUpdateEmailExist = dao.checkUpdateEmailExist(email, userID);
            if (dao.checkInputMail(email) == false) {
                error.setEmailError("Wrong input email!");
                check = false;
            }
            if (checkUpdateEmailExist != null) {
                error.setEmailError("Email is exist!");
                check = false;
            }
            String type = request.getParameter("usertype");
            if (type == null) {
                error.setTypeIDError("This field CAN NOT be EMPTY!!!");
                check = false;
            }
            String gender = request.getParameter("gender");
            if (gender == null) {
                error.setGenderError("This field CAN NOT be EMPTY!!!");
                check = false;
            }
            String phone = request.getParameter("phone");
            if (dao.checkInputPhoneNumber(phone) == false) {
                error.setPhoneNumberError("Wrong input phone number!");
                check = false;
            }
            String avatarUrl = request.getParameter("avatar");

            if (check == false) {
                String page = request.getParameter("page");
                request.setAttribute("edit", "Can Edit");
                request.setAttribute("ERROR", error);
                url = ERROR;
            } else {
                UserDAO updateProflie = new UserDAO();
                UserDTO user = new UserDTO(userID, userName, password, email, true, type, "US", gender, phone, avatarUrl);
                updateProflie.updateUserProfile(user);
                UserDTO getinfodto = new UserDTO();
                UserDAO getinfodao = new UserDAO();
                getinfodto = getinfodao.checkUserExist(userID);
                session.setAttribute("LOGIN_USER", getinfodto);
                url = SUCCESS;
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
