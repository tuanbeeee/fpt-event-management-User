/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.posts.EventDAO;
import sample.posts.EventPost;
import sample.posts.EventPostError;

/**
 *
 * @author tvfep
 */
@WebServlet(name = "AddAnEventController", urlPatterns = {"/AddAnEventController"})
public class AddAnEventController extends HttpServlet {

    private static final String ERROR = "EventTypeAndLocationController";
    private static final String SUCCESS = "EventListController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        long millis = System.currentTimeMillis();
        java.sql.Date nowDate = new java.sql.Date(millis);
        String url = ERROR;
        String id;
        EventPostError evtError = new EventPostError();
        EventDAO evtDao = new EventDAO();
        try {

            while (true) {
                id = "EVT" + ThreadLocalRandom.current().nextInt(1, 100000);
                if (!evtDao.checkEventIDDuplicate(id)) {
                    break;
                }
            }

            String takePlaceDate = request.getParameter("takePlaceDate");
            String content = request.getParameter("content");
            String title = request.getParameter("title");
            String location = request.getParameter("location");
            String imgUrl = request.getParameter("imgUrl");
            String eventType = request.getParameter("eventType");
            int numberOfView = 0;
            String speaker = request.getParameter("speaker");
            String summary = request.getParameter("summary");

            Date createDate = nowDate;

            Date takePlaceDateCheck = Date.valueOf(takePlaceDate);

            if (createDate.after(takePlaceDateCheck)) {
                evtError.setTakePlaceDate("Takeplace date must be after today!");
                request.setAttribute("ERROR", evtError);

            } else {
                EventPost event = new EventPost();
                boolean checkCreate = evtDao.createAnEvent(event);
                if (checkCreate == true) {
                    url = SUCCESS;

                }
            }

        } catch (Exception e) {
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
