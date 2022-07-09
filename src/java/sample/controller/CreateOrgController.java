/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.organization.OrganizationDAO;
import sample.organization.OrganizationDTO;
import sample.organization.OrganizationError;

/**
 *
 * @author light
 */
@WebServlet(name = "CreateOrgController", urlPatterns = {"/CreateOrgController"})
public class CreateOrgController extends HttpServlet {

    private static final String ERROR = "Admin_OrgForm.jsp";
    private static final String SUCCESS = "AdminOrgPageController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        boolean check = true;
        OrganizationDTO orgDTO = null;
        OrganizationDAO orgDAO = new OrganizationDAO();
        OrganizationError orgError = new OrganizationError();

        try {
            String orgID = request.getParameter("orgID");
            String orgName = request.getParameter("orgName");
            String description = request.getParameter("description");
            String imgUrl = request.getParameter("imgUrl");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            long millis = System.currentTimeMillis();
            java.sql.Date createDate = new java.sql.Date(millis);

            //=================CHECK==================== 
            if (orgID == null || orgID.isEmpty()) {
                orgError.setOrgIDError("This field can not be empty!!");
                check = false;
            } else if (orgDAO.getOrganization(orgID) != null) {
                orgError.setOrgIDError("The ID has been exist");
                check = false;
            } else if (orgID.length() != 3) {
                orgError.setOrgIDError("The ID must have 3 character!!");
                check = false;
            }

            if (orgName == null || orgName.isEmpty()) {
                orgError.setOrgNameError("This field can not be empty!!");
                check = false;
            }

            if (description == null || description.isEmpty()) {
                orgError.setDescriptionError("This field can not be empty!!");
                check = false;
            }

            if (imgUrl == null || imgUrl.isEmpty()) {
                orgError.setImgUrlError("This field can not be empty!!");
                check = false;
            }
            


            //==========================================
            if (check) {
                orgDTO = new OrganizationDTO(orgID.toUpperCase(), orgName, createDate.toString(), description, imgUrl, status);
                if (orgDAO.createOrg(orgDTO)) {
                    request.setAttribute("SUCCESS", "CREATE SUCCESSFULLY!!");
                    url = SUCCESS;
                }
            } else
                request.setAttribute("ERROR", orgError);

        } catch (Exception e) {
            log("Error at CreateOrgController " + e.toString());
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
