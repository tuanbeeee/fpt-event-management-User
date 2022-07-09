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
import sample.organization.OrganizationDTO;
import sample.users.UserDAO;
import sample.users.UserError;

/**
 *
 * @author Tuan Be
 */
@WebServlet(name = "OrgRegisterController", urlPatterns = {"/OrgRegisterController"})
public class OrgRegisterController extends HttpServlet {

    private static final String ERROR = "Org_Register.jsp";
    private static final String SUCCESS = "Org_Register.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = true;
        UserDAO dao = new UserDAO();
        OrganizationDTO dto = new OrganizationDTO();
        UserError error = new UserError();
        try {

            String orgID = request.getParameter("orgID");
            OrganizationDTO checkOrgIDExist = dao.checkOrgIDExist(orgID);

            if (dao.checkInputOrgID(orgID) == false) {
                error.setIdError("User name must be 3-16 characters!");
                check = false;
            } else if (checkOrgIDExist != null) {
                error.setIdError("This OrgID has existed!");
                check = false;
            }
            String clubName = request.getParameter("clubName");

            String email = request.getParameter("email");
            OrganizationDTO checkOrgEmailExist = dao.checkOrgeEmailExist(email);
            if (dao.checkInputMail(email) == false) {
                error.setEmailError("Wrong input email!");
                check = false;
            }
            if (checkOrgEmailExist != null) {
                error.setEmailError("Email is exist!");
                check = false;
            }
            String imgURL = request.getParameter("imgURL");
            String description = request.getParameter("description");
            String createDate = request.getParameter("createDate");

            String password = request.getParameter("password");

            String repass = request.getParameter("confirm");
            if (!password.equals(repass)) {
                error.setPasswordConfirmError("Wrong confirm password!");
                check = false;
            }

            boolean status = true;
            String statusTypeID = "PE";

            if (check == false) {
                request.setAttribute("ERROR", error);
                url = ERROR;
            } else {
                dto = new OrganizationDTO(orgID, clubName, createDate, description, imgURL, status, email, statusTypeID);
                if (dao.signUpByOrg(dto)) {
                    request.setAttribute("Message", "Successfully submitted account registration request.\n"
                            + "Please wait for confirmation !");
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
