/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import sample.users.ParticipantsDTO;
import sample.users.UserDAO;

/**
 *
 * @author Tuan Be
 */
@WebServlet(name = "UserParticipantsEvent", urlPatterns = {"/UserParticipantsEvent"})
public class UserParticipantsEvent extends HttpServlet {

    private static final String SUCCESS = "UserViewEventDetail";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String url = ERROR;
        try {
            UserDAO dao = new UserDAO();
            ParticipantsDTO dto = new ParticipantsDTO();
            String userID = request.getParameter("username");
            String eventID = request.getParameter("eventID");

            dto = dao.getParticipants(userID, eventID);
            if (request.getParameter("PARTICIPANTS") != null) {
                if (dto == null && dao.getUnparticipants(userID, eventID) == null) {
                    ByteArrayOutputStream out = QRCode.from("http://localhost:8084/EventManagement/MainController?action=readQRCode&userID=" + userID + "&eventID=" + eventID + "").to(
                            ImageType.PNG).stream();
                    FileOutputStream fout = new FileOutputStream(new File(
                            "D:\\QR-Code\\" + userID + "_" + eventID + ".JPG"));
                    fout.write(out.toByteArray());
                    fout.flush();
                    fout.close();
                    dao.participantsUser(userID, eventID);
                    url = SUCCESS;
                } else if (dao.getUnparticipants(userID, eventID) != null) {
                    dao.participantAgainByUser(userID, eventID);
                    url = SUCCESS;
                }
            } else if (request.getParameter("UNPARTICIPANTS") != null) {
                if (dto != null) {
                    dao.unparticipantByUser(userID, eventID);
                    url = SUCCESS;
                }
            }

            request.setAttribute("EVENT_DETAIL_ID", eventID);

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
