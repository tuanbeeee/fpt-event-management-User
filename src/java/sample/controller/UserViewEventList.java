/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.eventtype.EventTypeDTO;
import sample.notification.NotificationDAO;
import sample.notification.NotificationDTO;
import sample.posts.EventDAO;
import sample.posts.EventPost;
import sample.users.UserDAO;

/**
 *
 * @author Tuan Be
 */
@WebServlet(name = "UserViewEventList", urlPatterns = {"/UserViewEventList"})
public class UserViewEventList extends HttpServlet {

    private static final String SUCCESS = "User_View_Event_List.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String search = request.getParameter("search");
            String type = request.getParameter("type");
            EventDAO evtDAO = new EventDAO();
            NotificationDAO notiDAO = new NotificationDAO();

            if (search == null && type == null) {
                ArrayList<EventPost> list = new ArrayList<>();
                list = evtDAO.getAllEventList();
                request.setAttribute("VIEW_EVENT_LIST", list);

            } else if (request.getParameter("search") != null && type == null) {
                ArrayList<EventPost> listEvtWithoutMark = new ArrayList<>();
                ArrayList<EventPost> listEvt = new ArrayList<>();
                listEvt = evtDAO.searchEvent(search);
                listEvtWithoutMark = evtDAO.searchEventWithoutMark(search);
                if (listEvt.size() == 0 && listEvtWithoutMark.size() != 0) {
                    request.setAttribute("VIEW_EVENT_LIST", listEvtWithoutMark);
                } else if (listEvt.size() != 0 && listEvtWithoutMark.size() == 0) {
                    request.setAttribute("VIEW_EVENT_LIST", listEvt);
                } else if (listEvt.size() != 0 && listEvtWithoutMark.size() != 0) {
                    request.setAttribute("VIEW_EVENT_LIST", listEvt);
                } else {
                    request.setAttribute("Message", "Nothing to show.....");
                }
            } else if (request.getParameter("search") == null && type != null) {
                ArrayList<EventPost> listEvtByType = new ArrayList<>();
                listEvtByType = evtDAO.getEventByTpye(type);
                System.out.println(listEvtByType);
                if (listEvtByType.size() != 0) {
                    request.setAttribute("VIEW_EVENT_LIST", listEvtByType);
                } else {
                    request.setAttribute("Message", "Nothing to show.....");
                }
            }

            String user = request.getParameter("userID");
            if (user != null) {
                ArrayList<NotificationDTO> getNoti = new ArrayList<>();
                getNoti = notiDAO.getNotification(user);
                request.setAttribute("GET_NOTIFICATION", getNoti);
            }

            ArrayList<EventTypeDTO> getEvtType = new ArrayList<EventTypeDTO>();
            getEvtType = evtDAO.getAllEventType();
            request.setAttribute("EVENT_TYPE", getEvtType);

            url = SUCCESS;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
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
