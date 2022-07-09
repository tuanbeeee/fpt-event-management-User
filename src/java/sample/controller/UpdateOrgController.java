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
import sample.organization.OrganizationDAO;
import sample.organization.OrganizationDTO;
import sample.organization.OrganizationError;

/**
 *
 * @author light
 */
@WebServlet(name = "UpdateOrgController", urlPatterns = {"/UpdateOrgController"})
public class UpdateOrgController extends HttpServlet {

    private static final String ERROR = "Admin_OrgForm.jsp";
    private static final String SUCCESS = "Admin_OrgForm.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = true;
        OrganizationDTO orgDTO = null;
        OrganizationDAO orgDAO = new OrganizationDAO();
        OrganizationError orgError = new OrganizationError();
        try {
            String id = request.getParameter("id");
            String oldOrgID = request.getParameter("oldOrgID");
            if (id == null || id.isEmpty()) {
                String orgID = request.getParameter("orgID");
                String orgName = request.getParameter("orgName");
                String description = request.getParameter("description");
                String imgUrl = request.getParameter("imgUrl");
                boolean status = Boolean.parseBoolean(request.getParameter("status"));

                if (orgID == null || orgID.isEmpty()) {
                    orgError.setOrgIDError("This field can not be empty!!");
                    check = false;
                } else if (!oldOrgID.equals(orgID)) { //cái cũ != cái mới 123vs234
                    if (orgDAO.getOrganization(orgID) != null) { //tìm orgID
                        orgError.setOrgIDError("The ID has been exist");
                        check = false;
                    } else if (orgID.length() != 3) {
                        orgError.setOrgIDError("The ID must have 3 character!!");
                        check = false;
                    }
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

                if (check) {
                    orgDTO = new OrganizationDTO(orgID.toUpperCase(), orgName, description, imgUrl, status);
                    if (orgDAO.updateOrg(orgDTO, oldOrgID)) {
                        request.setAttribute("SUCCESS", "Updated Successfully!!!");
                        request.setAttribute("ORG", orgDTO);
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", orgError);
                    orgDTO = new OrganizationDAO().getOrganization(oldOrgID);
                    request.setAttribute("ORG", orgDTO);
                }
            } else {
                orgDTO = new OrganizationDAO().getOrganization(id);
                request.setAttribute("ORG", orgDTO);
            }

        } catch (Exception e) {
            log("Error at UpdateOrgController " + e.toString());
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
