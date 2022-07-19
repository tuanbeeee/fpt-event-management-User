/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author light
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "error.jsp";

    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGIN = "Login";

    private static final String REGISTER_CONTROLLER = "RegisterController";
    private static final String REGISTER = "Register";

    private static final String LOGIN_PAGE_CONTROLLER = "UserDataController";
    private static final String LOGIN_PAGE = "loginpage";

    private static final String DATA_TYPE_CONTROLLER = "UserDataController";
    private static final String DATA_TYPE = "dataType";

    private static final String PROFILE_PAGE_CONTROLLER = "UserProfileController";
    private static final String PROFILE_PAGE = "profilepage";

    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String LOGOUT = "logout";

    private static final String HOME_PAGE_CONTROLLER = "UserHomePage";
    private static final String HOME_PAGE = "homepage";

    private static final String USER_UPDATE_PROFILE_CONTROLLER = "UpdateProfileController";
    private static final String USER_UPDATE_PROFILE = "updateprofile";

    private static final String USER_VIEW_EVENT_LIST = "viewEventList";
    private static final String USER_VIEW_EVENT_LIST_CONTROLLER = "UserViewEventList";

    private static final String USER_VIEW_EVENT_DETAIL = "viewEventDetail";
    private static final String USER_VIEW_EVENT_DETAIL_CONTROLLER = "UserViewEventDetail";

    private static final String LIST_EVENT = "ListEvent";
    private static final String LIST_EVENT_CONTROLLER = "EventListController";

    private static final String SEARCH_EVENT = "SearchEvent";
    private static final String SEARCH_CONTROLLER = "SearchController";

    private static final String EVENT_DETAIL = "EventDetail";
    private static final String DETAIL_CONTROLLER = "DetailController";

    private static final String USER_VIEW_CLUB_LIST = "clubList";
    private static final String USER_VIEW_CLUB_LIST_CONTROLLER = "UserViewClubList";

    private static final String USER_PARTICIPANTS = "UserParticipants";
    private static final String USER_PARTICIPANTS_CONTROLLER = "UserParticipantsEvent";

    private static final String USER_VIEW_CLUB_DETAIL = "ClubInfo";
    private static final String USER_VIEW_CLUB_DETAIL_CONTROLLER = "UserViewClubDetail";

    private static final String ORG_FOLLOWER = "OrgFollower";
    private static final String ORG_FOLLOWER_CONTROLLER = "UserFollowClubController";

    private static final String VIEW_BLOG_LIST = "ViewBlogList";
    private static final String VIEW_BLOG_LIST_CONTROLLER = "UserViewBlogListController";

    private static final String VIEW_BLOG_DETAIL = "ViewBlogDetail";
    private static final String VIEW_BLOG_DETAIL_CONTROLLER = "UserViewBlogDetailController";

    private static final String COMMNENT_BY_USER = "UserCommemt";
    private static final String COMMNENT_BY_USER_CONTROLLER = "UserCommentToEventController";

    private static final String SIGN_UP_BY_ORG = "Sign Up By Org";
    private static final String SIGN_UP_BY_ORG_CONTROLLER = "OrgRegisterController";

    private static final String FEEDBACK_BY_USER = "feedback";
    private static final String FEEDBACK_BY_USER_CONTROLLER = "UserFeedbackEventController";

    private static final String SEARCH_EVENT_BY_USER = "search";
    private static final String SEARCH_EVENT_BY_USER_CONTROLLER = "UserViewEventList";

    private static final String DELETE_COMMENT_BY_USER = "deleteComment";
    private static final String DELETE_COMMENT_BY_USER_CONTROLLER = "UserDeleteCommentController";

    private static final String CHANGE_PASS_BY_USER = "changePassword";
    private static final String CHANGE_PASS_BY_USER_CONTROLLER = "UserChangePasswordController";

    private static final String SEARCH_CLUB_BY_USER = "searchClub";
    private static final String SEARCH_CLUB_BY_USER_CONTROLLER = "UserViewClubList";

    private static final String SEARCH_BLOG_BY_USER = "searchBlog";
    private static final String SEARCH_BLOG_BY_USER_CONTROLLER = "UserViewBlogListController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;

            } else if (REGISTER.equals(action)) {
                url = REGISTER_CONTROLLER;

            } else if (LOGIN_PAGE.equals(action)) {
                url = LOGIN_PAGE_CONTROLLER;

            } else if (DATA_TYPE.equals(action)) {
                url = DATA_TYPE_CONTROLLER;

            } else if (PROFILE_PAGE.equals(action)) {
                url = PROFILE_PAGE_CONTROLLER;

            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;

            } else if (HOME_PAGE.equals(action)) {
                url = HOME_PAGE_CONTROLLER;

            } else if (USER_UPDATE_PROFILE.equals(action)) {
                url = USER_UPDATE_PROFILE_CONTROLLER;

            } else if (SEARCH_EVENT.equals(action)) {
                url = SEARCH_CONTROLLER;

            } else if (EVENT_DETAIL.equals(action)) {
                url = DETAIL_CONTROLLER;

            } else if (LIST_EVENT.equals(action)) {
                url = LIST_EVENT_CONTROLLER;

            } else if (USER_VIEW_EVENT_LIST.equals(action)) {
                url = USER_VIEW_EVENT_LIST_CONTROLLER;

            } else if (USER_VIEW_EVENT_DETAIL.equals(action)) {
                url = USER_VIEW_EVENT_DETAIL_CONTROLLER;

            } else if (USER_VIEW_CLUB_LIST.equals(action)) {
                url = USER_VIEW_CLUB_LIST_CONTROLLER;

            } else if (USER_PARTICIPANTS.equals(action)) {
                url = USER_PARTICIPANTS_CONTROLLER;

            } else if (USER_VIEW_CLUB_DETAIL.equals(action)) {
                url = USER_VIEW_CLUB_DETAIL_CONTROLLER;

            } else if (ORG_FOLLOWER.equals(action)) {
                url = ORG_FOLLOWER_CONTROLLER;

            } else if (VIEW_BLOG_LIST.equals(action)) {
                url = VIEW_BLOG_LIST_CONTROLLER;

            } else if (VIEW_BLOG_DETAIL.equals(action)) {
                url = VIEW_BLOG_DETAIL_CONTROLLER;

            } else if (COMMNENT_BY_USER.equals(action)) {
                url = COMMNENT_BY_USER_CONTROLLER;

            } else if (SIGN_UP_BY_ORG.equals(action)) {
                url = SIGN_UP_BY_ORG_CONTROLLER;

            } else if (FEEDBACK_BY_USER.equals(action)) {
                url = FEEDBACK_BY_USER_CONTROLLER;

            } else if (SEARCH_EVENT_BY_USER.equals(action)) {
                url = SEARCH_EVENT_BY_USER_CONTROLLER;

            } else if (DELETE_COMMENT_BY_USER.equals(action)) {
                url = DELETE_COMMENT_BY_USER_CONTROLLER;

            } else if (CHANGE_PASS_BY_USER.equals(action)) {
                url = CHANGE_PASS_BY_USER_CONTROLLER;

            } else if (SEARCH_CLUB_BY_USER.equals(action)) {
                url = SEARCH_CLUB_BY_USER_CONTROLLER;

            } else if (SEARCH_BLOG_BY_USER.equals(action)) {
                url = SEARCH_BLOG_BY_USER_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController" + e.toString());
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
