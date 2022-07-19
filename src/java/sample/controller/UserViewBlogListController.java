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
import sample.blog.BlogDTO;
import sample.notification.NotificationDAO;
import sample.notification.NotificationDTO;
import sample.posts.BlogDAO;
import sample.users.UserDAO;

/**
 *
 * @author Tuan Be
 */
@WebServlet(name = "UserViewBlogListController", urlPatterns = {"/UserViewBlogListController"})
public class UserViewBlogListController extends HttpServlet {

    private static final String SUCCESS = "User_View_Blog_List.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            String search = request.getParameter("search");
            UserDAO dao = new UserDAO();
            NotificationDAO notiDAO = new NotificationDAO();
            BlogDAO blgDAO = new BlogDAO();
            ArrayList<BlogDTO> page = new ArrayList<>();
            int countBlog = 0;
            int paging = 1;
            if (request.getParameter("pageging") == null) {
                paging = 1;
            } else {
                paging = Integer.parseInt(request.getParameter("pageging"));
            }
            if (search == null) {

                ArrayList<BlogDTO> list = new ArrayList<>();
                list = blgDAO.getBlogWithPaging(paging);
                request.setAttribute("BLOG_LIST", list);

            } else if (search != null) {

                ArrayList<BlogDTO> listBlogWithoutMark = new ArrayList<>();
                ArrayList<BlogDTO> listBlog = new ArrayList<>();

                listBlog = blgDAO.searchBlog(search);
                listBlogWithoutMark = blgDAO.searhBlogWithoutMark(search);

                if (listBlog.size() == 0 && listBlogWithoutMark.size() != 0) {
                    request.setAttribute("BLOG_LIST", listBlogWithoutMark);

                } else if (listBlog.size() != 0 && listBlogWithoutMark.size() == 0) {
                    request.setAttribute("BLOG_LIST", listBlog);

                } else if (listBlog.size() != 0 && listBlogWithoutMark.size() != 0) {
                    request.setAttribute("BLOG_LIST", listBlog);

                } else {
                    request.setAttribute("Message", "Nothing to show.....");
                }
            }
            page = blgDAO.getAllBlog();
            countBlog = page.size();
            int endPage = 0;
            endPage = countBlog / 4;
            if (countBlog % 4 != 0) {
                endPage++;
            }
            System.out.println(endPage);
            request.setAttribute("paging", endPage);

            String user = request.getParameter("userID");
            if (user != null) {
                ArrayList<NotificationDTO> getNoti = new ArrayList<>();
                getNoti = notiDAO.getNotification(user);
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
