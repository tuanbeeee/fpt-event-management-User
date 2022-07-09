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
import sample.users.UserDAO;
import sample.users.UserDTO;
import sample.users.UserError;

/**
 *
 * @author light
 */
@WebServlet(name = "UpdateUserController", urlPatterns = {"/UpdateUserController"})
public class UpdateUserController extends HttpServlet {

    private static final String ERROR = "UserDataController";
    private static final String SUCCESS = "UserDataController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = true;
        UserDTO user = new UserDTO();
        UserDTO oldUser = new UserDTO();

        UserDAO dao = new UserDAO();
        UserError error = new UserError();
        try {
            String id = request.getParameter("id");

            if (id == null) {
                String userName = request.getParameter("username");
                oldUser = dao.checkUserExist(userName);
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                UserDTO checkEmailExist = dao.checkEmailExist(email);
                if (dao.checkInputMail(email) == false) {
                    error.setEmailError("Wrong input email!");
                    check = false;
                }
                if (checkEmailExist != null) {
                    if (!checkEmailExist.getEmail().toUpperCase().equals(oldUser.getEmail().toUpperCase())) {
                        error.setEmailError("Email is exist!");
                        check = false;

                    }
                }
                String type = request.getParameter("userType");

                if (type == null) {
                    error.setTypeIDError("This field CAN NOT be EMPTY!!!");
                    check = false;
                }

                String role = "US";
                String avtURL = request.getParameter("imgUrl");
                String gender = request.getParameter("gender");
                String phone = request.getParameter("phone");
                if (dao.checkInputPhoneNumber(phone) == false) {
                    error.setPhoneNumberError("Wrong input phone number!");
                    check = false;
                }

                String password = request.getParameter("password");
                String repass = request.getParameter("confirm");
                if (password == "" && repass == "") {
                    password = oldUser.getPassword();
                } else if (!password.equals(repass)) {
                    error.setPasswordConfirmError("Wrong confirm password!");
                    check = false;
                }

                boolean status = Boolean.parseBoolean(request.getParameter("status"));

                if (check == true) {
                    user = new UserDTO(oldUser.getId(), fullName, password, email, status, type, role, gender, phone, avtURL);
                    if (dao.updateUserProfileByAdmin(user)) {
                        request.setAttribute("SUCCESS", "UPDATED SUCCESSFULLY!!!");
                        request.setAttribute("USER", dao.checkUserExist(oldUser.getId()));
                        url = SUCCESS;
                    } else {
                        request.setAttribute("USER", oldUser);
                    }

                } else {
                    request.setAttribute("USER", oldUser);
                }
            } else {
                request.setAttribute("USER", dao.checkUserExist(id));
            }

        } catch (Exception e) {
            log("Error at UpdateUserContrller" + e.toString());
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
