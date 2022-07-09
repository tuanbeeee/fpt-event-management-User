/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.posts.EventDAO;
import sample.posts.EventPost;

/**
 *
 * @author tvfep
 */
@WebServlet(name = "UpdateEventController", urlPatterns = {"/UpdateEventController"})
public class UpdateEventController extends HttpServlet {

    private static final String SUCCESS = "EventListController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        long millis = System.currentTimeMillis();
        java.sql.Date nowDate = new java.sql.Date(millis);
        String url = "updateEvent.jsp";

        EventDAO evtDao = new EventDAO();
        try {
            String id = request.getParameter("eventID");
            String takePlaceDate = request.getParameter("takePlaceDate");
            String content = request.getParameter("content");
            String title = request.getParameter("title");
            String location = request.getParameter("location");
            String imgUrl = request.getParameter("imgUrl");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String eventType = request.getParameter("eventType");
            int numberOfView = Integer.parseInt(request.getParameter("numberOfView"));
            String speaker = request.getParameter("speaker");
            String summary = request.getParameter("summary");

            Date createDate = nowDate;
            Date takePlaceDateCheck = Date.valueOf(takePlaceDate);

            boolean checkDuplicate = evtDao.checkEventIDDuplicate(id);
            if (checkDuplicate) {
                request.setAttribute("CHECK_DUPLICATE", "Aready have a event with eventID: " + id + "");
            } else if (createDate.after(takePlaceDateCheck)) {
                request.setAttribute("CHECK_DATE", "Take place date must after today!");
            } else {
                EventPost event = new EventPost();
                boolean checkUpdate = evtDao.updateAnEvent(event);
                if (checkUpdate == true) {
                    url = SUCCESS;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateEventController.class.getName()).log(Level.SEVERE, null, ex);
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
