/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.comment.CommentSectionDTO;
import sample.notification.NotificationDTO;
import sample.posts.EventPost;
import sample.users.ParticipantsDTO;
import sample.users.UserDAO;

/**
 *
 * @author Tuan Be
 */
@WebServlet(name = "UserViewEventDetail", urlPatterns = {"/UserViewEventDetail"})
public class UserViewEventDetail extends HttpServlet {

    private static final String SUCCESS = "User_View_Event_Details.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            String getEventIDForEventDetail = (String) request.getAttribute("EVENT_DETAIL_ID");

            String getEventIDWhenComment = (String) request.getAttribute("eventID");
   

            String getUserIDWhenComment = (String) request.getAttribute("userName");
            
            String userID = null;
            String eventID = null;

            if (getEventIDForEventDetail != null) {
                eventID = getEventIDForEventDetail;

            } else if (getEventIDWhenComment != null) {
                eventID = getEventIDWhenComment;
            } else {
                eventID = request.getParameter("EVENT_ID");
            }
            
            if (getUserIDWhenComment != null){
                userID = getUserIDWhenComment;
            } else {
                userID = request.getParameter("username");
            }

            UserDAO dao = new UserDAO();

            int view = dao.getAnEvent(eventID).getNumberOfView() + 1;
            EventPost numberOfView = new EventPost(eventID, view);
            dao.countNumberOfView(numberOfView);
//            System.out.println(eventID);
            ParticipantsDTO dto = new ParticipantsDTO();
            dto = dao.getParticipants(userID, eventID);
            request.setAttribute("CHECK_PARTICIPANTS", dto);
//            System.out.println(dto);

            EventPost getEventPostDetail = dao.getAnEvent(eventID);
            request.setAttribute("USER_VIEW_EVENT_DETAIL", getEventPostDetail);
            
            ArrayList<ParticipantsDTO> list = new ArrayList<>();
            list = dao.getParticipantsList(eventID);
            int participationLimit = 99 + list.size();
            request.setAttribute("PARTICIPATIONLIMIT", participationLimit);

            ArrayList<CommentSectionDTO> comment = new ArrayList<CommentSectionDTO>();
            comment = dao.getComment(eventID);
            request.setAttribute("COMMENT", comment);
            
            if (userID != null) {
                ArrayList<NotificationDTO> getNoti = new ArrayList<>();
                getNoti = dao.getNotification(userID);
                request.setAttribute("GET_NOTIFICATION", getNoti);
            }

            url = SUCCESS;
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
