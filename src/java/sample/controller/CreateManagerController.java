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
import sample.users.ManagerDTO;
import sample.users.UserDAO;
import sample.users.UserDTO;
import sample.users.UserError;

/**
 *
 * @author light
 */
@WebServlet(name = "CreateManagerController", urlPatterns = {"/CreateManagerController"})
public class CreateManagerController extends HttpServlet {

    private static final String ERROR = "UserDataController";
    private static final String SUCCESS = "UserDataController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ManagerDTO dto = new ManagerDTO();
        UserDAO dao = new UserDAO();
        UserError error = new UserError();
        boolean check = true;
        try {
            String userName = request.getParameter("username");
            UserDTO checkUserExist = dao.checkUserExist(userName);

            if (dao.checkInputUserID(userName) == false) {
                error.setIdError("User name must be 8-32 characters!");
                check = false;
            } else if (checkUserExist != null) {
                error.setIdError("This User Name has existed!");
                check = false;
            }

            String fullName = request.getParameter("fullName");

            String email = request.getParameter("email");
            UserDTO checkEmailExist = dao.checkEmailExist(email);
            if (dao.checkInputMail(email) == false) {
                error.setEmailError("Wrong input email!");
                check = false;
            }
            if (checkEmailExist != null) {
                error.setEmailError("Email is exist!");
                check = false;
            }
            String type = request.getParameter("userType");

            if (type == null) {
                error.setTypeIDError("This field CAN NOT be EMPTY!!!");
                check = false;
            }

            String role = "CLB";
            String orgID = request.getParameter("orgID");
            if ("FPT".equals(orgID)) {
                role = "MOD";
            }

            String avtURL = request.getParameter("imgUrl");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            if (dao.checkInputPhoneNumber(phone) == false) {
                error.setPhoneNumberError("Wrong input phone number!");
                check = false;
            }
            String password = request.getParameter("password");

            String repass = request.getParameter("confirm");
            if (!password.equals(repass)) {
                error.setPasswordConfirmError("Wrong confirm password!");
                check = false;
            }

            boolean status = Boolean.parseBoolean(request.getParameter("status"));
//            if (page.equals("Admin_UserForm.jsp")) {
//                status = Boolean.parseBoolean(request.getParameter("status"));
//            } else {
//                status = true;
//            }

            if (check == false) {
                request.setAttribute("ERROR", error);
            } else {
                dto = new ManagerDTO(orgID, userName, fullName, password, email, status, type, role, gender, phone, avtURL);
                if (dao.signUpByUser(dto)) {
                    if (dao.signUpByManager(dto)) {
                        request.setAttribute("Message", "CREATE ACCOUNT SUCCESSFULLY!");
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at CreateManagerController " + e.toString());
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
