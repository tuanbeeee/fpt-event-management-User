/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.users;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.naming.NamingException;
import sample.blog.BlogDTO;
import sample.comment.CommentSectionDTO;
import sample.eventtype.EventTypeDTO;
import sample.feedback.UserFeedbackDTO;
import sample.notification.NotificationDTO;
import sample.organization.OrganizationDAO;
import sample.organization.OrganizationDTO;
import sample.organization.OrganizationFollowerDTO;
import sample.posts.EventPost;
import sample.util.DBUtils;

/**
 *
 * @author light
 */
public class UserDAO {

    private static final long ONE_DAY_MILLI_SECONDS = 24 * 60 * 60 * 1000;

    private static final String GET_ALL_INFO_WITH_USERID = "SELECT userID, fullName, password, email, status, tblUserTypes.typeID, tblUserTypes.typeName, roleID, gender, phone, avatarUrl FROM tblUsers, tblUserTypes WHERE tblUsers.typeID = tblUserTypes.typeID AND userID=? ";

    private static final String GET_ALL_INFO_WITH_EMAIL = "SELECT userID, fullName, password, email, status, typeID, roleID, gender, phone, avatarUrl FROM tblUsers WHERE email=?";

    private static final String GET_ROLE = "SELECT userID, password, roleID, status FROM tblUsers WHERE userID=? AND password=?";

    private static final String SIGN_UP = "INSERT INTO tblUsers (userID, fullName, password, email, status, typeID, roleID, gender, phone, avatarUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ALL_TYPE = "SELECT typeID, typeName FROM tblUserTypes";

    private static final String GET_ALL_ORG = "SELECT orgID, orgName FROM tblOrgPage WHERE status = '1'";

    private static final String UPDATE_USER_PROFILE = "UPDATE tblUsers SET fullName = ?, email=?, typeID =?, gender=?, phone=?, avatarUrl=? WHERE userID=?";

    private static final String GET_ALL_INFO_MANAGER = "SELECT userID, fullName, password, email, status, roleID, gender, phone, avatarURL, orgID, tblUserTypes.typeID, tblUserTypes.typeName\n"
            + "FROM tblUsers, tblManagers, tblUserTypes\n"
            + "WHERE tblUsers.userID = tblManagers.managerID AND tblUsers.userID = ? AND tblUsers.typeID = tblUserTypes.typeID";

    private static final String LOGIN = "SELECT FROM WHERE";

    private static final String GET_ALL_USERS = "SELECT userID, fullName, password, email, status, roleID, gender, phone, avatarUrl, tblUserTypes.typeID, tblUserTypes.typeName FROM tblUsers, tblUserTypes WHERE tblUsers.typeID = tblUserTypes.typeID AND roleID = 'US'";

    private static final String GET_ALL_MANAGERS = "SELECT userID, fullName, password, email, status, roleID, gender, phone, avatarURL, orgID, tblUserTypes.typeID, tblUserTypes.typeName\n"
            + "FROM tblUsers, tblManagers, tblUserTypes\n"
            + "WHERE tblUsers.userID = tblManagers.managerID AND tblUsers.typeID = tblUserTypes.typeID";
    private static final String SEARCH_USER = "SELECT  userID, fullName, password, email, status, typeID, roleID, gender, phone, avatarUrl \n"
            + "             FROM tblUsers \n"
            + "             WHERE dbo.ufn_removeMark(fullName) like ? or fullName like ? or userID like ?";

    private static final String SEARCH_MANAGER = "SELECT userID, fullName, password, email, status, typeID, roleID, gender, phone, avatarURL, orgID\n"
            + "from tblUsers, tblManagers\n"
            + "where tblUsers.userID = tblManagers.managerID and (dbo.ufn_removeMark(fullName) like ? or fullName like ? or userID like ?)";

    private static final String DELETE_USER = "UPDATE tblUsers SET status = '0' WHERE userID = ?";

    private static final String UPDATE_USER_PROFILE_BY_ADMIN = "UPDATE tblUsers SET fullName = ?, email=?, typeID =?, gender=?, phone=?, avatarUrl=?, password = ?, status = ?, roleID = ? "
            + "WHERE userID = ?";

    private static final String SIGN_UP_BY_MANAGER = "INSERT INTO tblManagers (managerID, orgID) VALUES (?, ?)";

    private static final String CHECK_UPDATE_EMAIL_EXIST = "SELECT userID, fullName, password, email, status, typeID, roleID, gender, phone, avatarUrl FROM tblUsers WHERE email = ? and userID != ?";

    private static final String VIEW_EVENT_LIST_BY_USER = "SELECT eventID, orgID, createDate, takePlaceDate, content, title, location, imgUrl, tblEventPost.eventTypeID, numberOfView, speaker, summary, \n"
            + "            tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes\n"
            + "            FROM tblEventPost, tblEventType, tblLocation, tblStatusType\n"
            + "            WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and\n"
            + "			tblEventPost.statusTypeID = tblStatusType.statusTypeID AND tblEventPost.status = ? AND tblStatusType.statusTypeID =?";

    private static final String VIEW_EVENT_DETAIL_BY_USER = "SELECT eventID, orgID, createDate, takePlaceDate, content, title, location, imgUrl, tblEventPost.eventTypeID, numberOfView, speaker, summary, tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes\n"
            + "                    FROM tblEventPost, tblEventType, tblLocation, tblStatusType\n"
            + "                    WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and\n"
            + "                    tblEventPost.statusTypeID = tblStatusType.statusTypeID AND eventID = ?";

    private static final String COUNT_NUMBER_OF_VIEW = "UPDATE tblEventPost SET  numberOfView = ? WHERE eventID = ?";

    private static final String GET_ALL_ORGANIZATION = "SELECT orgID, orgName, createDate, description, imgUrl, status\n"
            + "FROM tblOrgPage WHERE statusTypeID ='AP'";

    private static final String GET_ALL_EVENT_INFO_FOR_USER_HOMEPAGE = "SELECT eventID, tblEventPost.orgID as org_ID, tblEventPost.createDate as evt_CreateDate, takePlaceDate, content, title, location, tblEventPost.imgUrl as evt_Img, tblEventPost.eventTypeID as evt_TypeID, numberOfView, speaker, summary, tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes, tblOrgPage.imgUrl as org_Img, tblOrgPage.orgName as org_Name, tblOrgPage.description as org_Description \n"
            + "FROM tblEventPost, tblEventType, tblLocation, tblStatusType, tblOrgPage\n"
            + "WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and tblEventPost.statusTypeID = tblStatusType.statusTypeID and tblEventPost.orgID = tblOrgPage.orgID AND tblEventPost.status = ? AND tblStatusType.statusTypeID =?";

    private static final String GET_EVENT_BY_DATE = "SELECT eventID, tblEventPost.orgID as org_ID, tblEventPost.createDate as evt_CreateDate, takePlaceDate, content, title, location, tblEventPost.imgUrl as evt_Img, tblEventPost.eventTypeID as evt_TypeID, numberOfView, speaker, summary, tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes, tblOrgPage.imgUrl as org_Img, tblOrgPage.orgName as org_Name, tblOrgPage.description as org_Description \n"
            + "FROM tblEventPost, tblEventType, tblLocation, tblStatusType, tblOrgPage\n"
            + "WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and tblEventPost.statusTypeID = tblStatusType.statusTypeID and tblEventPost.orgID = tblOrgPage.orgID AND tblEventPost.status = ? AND tblStatusType.statusTypeID =? AND takePlaceDate =?";

    private static final String GET_NEWEST_EVENT = "SELECT TOP 4 eventID, tblEventPost.orgID as org_ID, tblEventPost.createDate as evt_CreateDate, takePlaceDate, \n"
            + "content, title, location, tblEventPost.imgUrl as evt_Img, tblEventPost.eventTypeID as evt_TypeID, \n"
            + "numberOfView, speaker, summary, tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, \n"
            + "eventTypeName, locationName, approvalDes, tblOrgPage.imgUrl as org_Img, tblOrgPage.orgName as org_Name, \n"
            + "tblOrgPage.description as org_Description \n"
            + "FROM tblEventPost, tblEventType, tblLocation, tblStatusType, tblOrgPage\n"
            + "WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and tblEventPost.statusTypeID = tblStatusType.statusTypeID and tblEventPost.orgID = tblOrgPage.orgID AND tblEventPost.status = ? AND tblStatusType.statusTypeID = ? ORDER BY eventID  DESC";

    private static final String GET_PARTICIPANTS = "SELECT userID, eventID, status FROM tblParticipants WHERE userID = ? AND eventID = ? AND status = '1'";

    private static final String PARTICIPANTS = "INSERT INTO tblParticipants (userID, eventID, status) VALUES (?, ?, '1')";

    private static final String GET_INFO_CLUB = "SELECT orgID, status, orgName, createDate, description, imgURL, email FROM tblOrgPage WHERE orgID =? AND status = '1' AND statusTypeID = 'AP'";

    private static final String GET_ALL_EVENT_INFO_FOR_CLUB_PROFILE = "SELECT eventID, tblEventPost.orgID as org_ID, tblEventPost.createDate as evt_CreateDate, takePlaceDate, content, title, location, tblEventPost.imgUrl as evt_Img, tblEventPost.eventTypeID as evt_TypeID, numberOfView, speaker, summary, tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes, tblOrgPage.imgUrl as org_Img, tblOrgPage.orgName as org_Name, tblOrgPage.description as org_Description\n"
            + "            FROM tblEventPost, tblEventType, tblLocation, tblStatusType, tblOrgPage\n"
            + "            WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and tblEventPost.statusTypeID = tblStatusType.statusTypeID and tblEventPost.orgID = tblOrgPage.orgID AND tblEventPost.status = ? AND tblStatusType.statusTypeID =? AND tblEventPost.orgID = ?";

    private static final String CHECK_USER_FOLLOWING = "SELECT userID, orgID, status FROM tblOrg_Follower WHERE userID = ? AND orgID = ? AND status = '1'";

    private static final String CHECK_USER_UNFOLLOW = "SELECT userID, orgID, status FROM tblOrg_Follower WHERE userID = ? AND orgID = ? AND status = '0'";

    private static final String FOLLOW_BY_USER = "INSERT INTO tblOrg_Follower (userID, orgID, status) VALUES (?, ?, '1')";

    private static final String UNFOLLOW_BY_USER = "UPDATE tblOrg_Follower SET status = '0' WHERE userID = ? AND orgID = ?";

    private static final String FOLLOW_AGAIN_BY_USER = "UPDATE tblOrg_Follower SET status = '1' WHERE userID = ? AND orgID = ?";

    private static final String GET_ALL_BLOG = "SELECT blogID, tblBlog.orgID AS org_ID, orgName, tblBlog.status AS Blog_status, title, tblBlog.createDate AS create_Date, content, tblBlog.imgUrl AS img_URL, numberOfView, summary FROM tblBlog, tblOrgPage WHERE tblBlog.orgID = tblOrgPage.orgID AND tblBlog.status = '1'";

    private static final String GET_BLOG_DETAIL = "SELECT blogID, tblBlog.orgID AS org_ID, orgName, tblBlog.status AS Blog_status, title, tblBlog.createDate AS create_Date, content, tblBlog.imgUrl AS img_URL, numberOfView, summary FROM tblBlog, tblOrgPage WHERE tblBlog.orgID = tblOrgPage.orgID AND tblBlog.status = '1' AND blogID = ?";

    private static final String COUNT_BLOG_VIEW_NUMBER = "UPDATE tblBlog SET  numberOfView = ? WHERE blogID = ?";

    private static final String GET_NEWEST_BLOG = "SELECT TOP 4 blogID, tblBlog.orgID AS org_ID, orgName, tblBlog.status AS Blog_status, title, tblBlog.createDate AS create_Date, content, tblBlog.imgUrl AS img_URL, numberOfView, summary FROM tblBlog, tblOrgPage WHERE tblBlog.orgID = tblOrgPage.orgID AND tblBlog.status = '1' ORDER BY blogID  DESC";

    private static final String GET_COMMENT = "SELECT commentID, replyID, tblCommentSections.status as sts, time, content, tblCommentSections.userID as id, tblUsers.fullName as name, tblUsers.avatarUrl as img , eventID FROM tblCommentSections, tblUsers WHERE tblUsers.userID = tblCommentSections.userID AND eventID = ? AND tblCommentSections.status = '1'";

    private static final String COMMENT = "INSERT INTO tblCommentSections (status ,time, content, userID, eventID) VALUES (? ,?, ?, ?, ?)";

    private static final String REPLY = "INSERT INTO tblCommentSections (replyID, status ,time, content, userID, eventID) VALUES (?, ? ,?, ?, ?, ?)";

    private static final String NOTIFICATION = "SELECT userID, tblUserNotification.eventID AS evtID, tblEventPost.content AS evtContent, notiDate, tblUserNotification.content AS notiContent, tblEventPost.orgID AS orgID, tblOrgPage.imgUrl AS orgImg FROM tblUserNotification, tblEventPost, tblOrgPage WHERE tblEventPost.orgID = tblOrgPage.orgID AND tblEventPost.eventID = tblUserNotification.eventID AND userID = ?";

    private static final String CHECK_ORGID_EXIST = "SELECT orgID, status, orgName, createDate, description, imgUrl, email, statusTypeID\n"
            + "FROM tblOrgPage \n"
            + "WHERE orgID = ? AND statusTypeID != 'DE' AND status = '1'";

    private static final String CHECK_ORG_EMAIL_EXIST = "SELECT orgID, status, orgName, createDate, description, imgUrl, email, statusTypeID\n"
            + "FROM tblOrgPage \n"
            + "WHERE email = ? AND statusTypeID != 'DE' AND status = '1' ";

    private static final String SIGN_UP_BY_ORG = "INSERT INTO tblOrgPage (orgID, status, orgName, createDate, description, imgUrl, email, statusTypeID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FEEDBACK_BY_USER = "INSERT INTO tblUser_Feedback (userID, eventID, content) VALUES (?, ?, ?)";

    private static final String SEARCH_EVENT_WITHOUT_MARK = "SELECT eventID, orgID, createDate, takePlaceDate, content, title, location, imgUrl, tblEventPost.eventTypeID, numberOfView, speaker, summary, \n"
            + "                       tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes\n"
            + "                       FROM tblEventPost, tblEventType, tblLocation, tblStatusType\n"
            + "                       WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and\n"
            + "            		   tblEventPost.statusTypeID = tblStatusType.statusTypeID AND  tblEventPost.status = 'True' AND tblStatusType.statusTypeID = 'AP' AND dbo.ufn_removeMark(title) like ?";

    private static final String SEARCH_EVENT = "SELECT eventID, orgID, createDate, takePlaceDate, content, title, location, imgUrl, tblEventPost.eventTypeID, numberOfView, speaker, summary, \n"
            + "                       tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes\n"
            + "                       FROM tblEventPost, tblEventType, tblLocation, tblStatusType\n"
            + "                       WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and\n"
            + "            		   tblEventPost.statusTypeID = tblStatusType.statusTypeID AND  tblEventPost.status = 'True' AND tblStatusType.statusTypeID = 'AP' AND title like ?";

    private static final String GET_EVENT_TYPE = "SELECT eventTypeID, eventTypeName, status FROM tblEventType WHERE status = '1'";

    private static final String GET_EVENT_BY_EVENT_TYPE = "SELECT eventID, orgID, createDate, takePlaceDate, content, title, location, imgUrl, tblEventPost.eventTypeID AS evtTypeID, numberOfView, speaker, summary, \n"
            + "                                   tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes\n"
            + "                                   FROM tblEventPost, tblEventType, tblLocation, tblStatusType\n"
            + "                                   WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and\n"
            + "                        		   tblEventPost.statusTypeID = tblStatusType.statusTypeID AND  tblEventPost.status = 'True' AND tblStatusType.statusTypeID = 'AP' AND tblEventPost.eventTypeID = ?";

    public ArrayList<EventTypeDTO> getAllEventType() throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_EVENT_TYPE;

        ArrayList<EventTypeDTO> lst = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int eventTypeID = rs.getInt("eventTypeID");
                    String eventTypeName = rs.getString("eventTypeName");
                    boolean status = rs.getBoolean("status");

                    EventTypeDTO cb = new EventTypeDTO(eventTypeID, eventTypeName, status);

                    lst.add(cb);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lst;

    }

    public UserDTO checkUserExist(String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        UserDTO user = null;
        UserDTO getInfoUser = null;
        try {
            String sql = GET_ALL_INFO_WITH_USERID;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            rs = ptm.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                boolean status = rs.getBoolean("status");
                String typeID = rs.getString("typeID");
                String typeName = rs.getString("typeName");
                String roleID = rs.getString("roleID");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String avatarUrl = rs.getString("avatarUrl");

                user = new UserDTO(userID, fullName, password, email, status, typeID, typeName, roleID, gender, phone, avatarUrl);
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;

    }

    public OrganizationDTO checkOrgIDExist(String orgID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        OrganizationDTO org = null;

        try {
            String sql = CHECK_ORGID_EXIST;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, orgID);
            rs = ptm.executeQuery();

            if (rs.next()) {
                String orgName = rs.getString("orgName");
                String createDate = rs.getString("createDate");
                String description = rs.getString("description");
                String imgUrl = rs.getString("imgUrl");
                String email = rs.getString("email");
                String statusTypeID = rs.getString("statusTypeID");
                boolean status = true;

                org = new OrganizationDTO(orgID, orgName, createDate, description, imgUrl, status, email, statusTypeID);
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return org;

    }

    public OrganizationDTO checkOrgeEmailExist(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        OrganizationDTO org = null;

        try {
            String sql = CHECK_ORG_EMAIL_EXIST;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, email);
            rs = ptm.executeQuery();

            if (rs.next()) {
                String orgName = rs.getString("orgName");
                String createDate = rs.getString("createDate");
                String description = rs.getString("description");
                String imgUrl = rs.getString("imgUrl");
                String orgID = rs.getString("orgID");
                String statusTypeID = rs.getString("statusTypeID");
                boolean status = true;

                org = new OrganizationDTO(orgID, orgName, createDate, description, imgUrl, status, email, statusTypeID);
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return org;

    }

    public ManagerDTO checkManagerExist(String managerID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ManagerDTO getInfoManager = null;
        try {
            String sql = GET_ALL_INFO_MANAGER;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, managerID);
            rs = ptm.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                boolean status = rs.getBoolean("status");
                String typeID = rs.getString("typeID");
                String typeName = rs.getString("typeName");
                String roleID = rs.getString("roleID");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String avatarUrl = rs.getString("avatarUrl");
                String orgID = rs.getString("orgID");

                getInfoManager = new ManagerDTO(orgID, managerID, fullName, password, email, status, typeID, roleID, gender, phone, avatarUrl);
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return getInfoManager;

    }

    public UserDTO checkEmailExist(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            String sql = GET_ALL_INFO_WITH_EMAIL;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, email);
            rs = ptm.executeQuery();

            if (rs.next()) {
                String userID = rs.getString("userID");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                boolean status = rs.getBoolean("status");
                String typeID = rs.getString("typeID");
                String roleID = rs.getString("roleID");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String avatarUrl = rs.getString("avatarUrl");

                user = new UserDTO(userID, fullName, password, email, status, typeID, roleID, gender, gender, phone, avatarUrl);

            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return user;

    }

    public UserDTO checkUpdateEmailExist(String email, String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            String sql = CHECK_UPDATE_EMAIL_EXIST;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, email);
            ptm.setString(2, userID);
            rs = ptm.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                boolean status = rs.getBoolean("status");
                String typeID = rs.getString("typeID");
                String roleID = rs.getString("roleID");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String avatarUrl = rs.getString("avatarUrl");

                user = new UserDTO(userID, fullName, password, email, status, typeID, roleID, gender, gender, phone, avatarUrl);

            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return user;

    }

    public boolean updateUserProfile(UserDTO user) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ptm = null;
        String sql = UPDATE_USER_PROFILE;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);

                ptm.setString(1, user.getName());
                ptm.setString(2, user.getEmail());
                ptm.setString(3, user.getTypeID());
                ptm.setString(4, user.getGender());
                ptm.setString(5, user.getPhoneNumber());
                ptm.setString(6, user.getPicture());
                ptm.setString(7, user.getId());

                ptm.executeUpdate();

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean updateUserProfileByAdmin(UserDTO user) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        String sql = UPDATE_USER_PROFILE_BY_ADMIN;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);

                ptm.setString(1, user.getName());
                ptm.setString(2, user.getEmail());
                ptm.setString(3, user.getTypeID());
                ptm.setString(4, user.getGender());
                ptm.setString(5, user.getPhoneNumber());
                ptm.setString(6, user.getPicture());
                ptm.setString(7, user.getPassword());
                ptm.setBoolean(8, user.isStatus());
                ptm.setString(9, user.getRoleID());
                ptm.setString(10, user.getId());

                if (ptm.executeUpdate() > 0) {
                    check = true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public String authenticateUser(String userID, String password) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String userNameDB = null;
        String passwordDB = null;
        String roleDB = null;
        String status = null;

        try {
            String sql = GET_ROLE;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, password);

            rs = ptm.executeQuery();

            while (rs.next()) {
                userNameDB = rs.getString("userID");
                passwordDB = rs.getString("password");
                roleDB = rs.getString("roleID");
                status = rs.getString("status");
                if (userID.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("CLB") && status.equals("1")) {
                    return "CLB";
                } else if (userID.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("FPT") && status.equals("1")) {
                    return "FPT";
                } else if (userID.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("US") && status.equals("1")) {
                    return "US";
                } else if (userID.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("ADM") && status.equals("1")) {
                    return "ADM";
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return "Invalid user !";
    }

    public ArrayList<UserDTO> getAllUserType() throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_ALL_TYPE;

        ArrayList<UserDTO> lst = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String typeID = rs.getString("typeID");
                    String typeName = rs.getString("typeName");

                    UserDTO cb = new UserDTO(typeID, typeName);

                    lst.add(cb);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lst;
    }

    public ArrayList<ManagerDTO> getAllOrg() throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_ALL_ORG;

        ArrayList<ManagerDTO> lst = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orgID = rs.getString("orgID");
                    String orgName = rs.getString("orgName");

                    ManagerDTO cb = new ManagerDTO(orgID, orgName);

                    lst.add(cb);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lst;
    }

    public boolean signUpByUser(UserDTO user) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean check = false;
        String sql = SIGN_UP;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, user.getId());
            ptm.setString(2, user.getName());
            ptm.setString(3, user.getPassword());
            ptm.setString(4, user.getEmail());
            ptm.setBoolean(5, user.isStatus());
            ptm.setString(6, user.getTypeID());
            ptm.setString(7, user.getRoleID());
            ptm.setString(8, user.getGender());
            ptm.setString(9, user.getPhoneNumber());
            ptm.setString(10, user.getPicture());
            if (ptm.executeUpdate() > 0) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean signUpByOrg(OrganizationDTO org) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean check = false;
        String sql = SIGN_UP_BY_ORG;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, org.getOrgID());
            ptm.setBoolean(2, org.isStatus());
            ptm.setString(3, org.getOrgName());
            ptm.setString(4, org.getCreateDate());
            ptm.setString(5, org.getDescription());
            ptm.setString(6, org.getImgUrl());
            ptm.setString(7, org.getEmail());
            ptm.setString(8, org.getStatusTypeID());
            if (ptm.executeUpdate() > 0) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean feedbackByUser(UserFeedbackDTO fb) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean check = false;
        String sql = FEEDBACK_BY_USER;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, fb.getUserID());
            ptm.setString(2, fb.getEventID());
            ptm.setString(3, fb.getContent());
            if (ptm.executeUpdate() > 0) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean signUpByManager(ManagerDTO user) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean check = false;
        String sql = SIGN_UP_BY_MANAGER;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, user.getId());
            ptm.setString(2, user.getOrgID());

            if (ptm.executeUpdate() > 0) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkInputPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile("^[0-9]{10}$");
        if (p.matcher(phoneNumber).find()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkInputMail(String mail) {
        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}$");
        if (p.matcher(mail).find()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkInputUserID(String userID) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{6,32}$");
        if (p.matcher(userID).find()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkInputOrgID(String userID) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{3,16}$");
        if (p.matcher(userID).find()) {
            return true;
        } else {
            return false;
        }
    }

    public List<UserDTO> getAllUsers() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<UserDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GET_ALL_USERS);
            rs = ptm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String typeID = rs.getString("typeID");
                String typeName = rs.getString("typeName");
                String roleID = rs.getString("roleID");
                String gender = rs.getString("gender");
                String phoneNumber = rs.getString("phone");
                String avatarUrl = rs.getString("avatarUrl");
                boolean status = rs.getBoolean("status");
                list.add(new UserDTO(userID, fullName, password, email, status, typeID, typeName, roleID, gender, phoneNumber, avatarUrl));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<ManagerDTO> getAllManagers() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ManagerDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GET_ALL_MANAGERS);
            rs = ptm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String typeID = rs.getString("typeID");
                String typeName = rs.getString("typeName");
                String roleID = rs.getString("roleID");
                String gender = rs.getString("gender");
                String phoneNumber = rs.getString("phone");
                String avatarUrl = rs.getString("avatarUrl");
                String orgID = rs.getString("orgID");
                boolean status = rs.getBoolean("status");
                list.add(new ManagerDTO(orgID, userID, fullName, password, email, status, typeID, typeName, roleID, gender, phoneNumber, avatarUrl));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<UserDTO> searchUser(String search) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<UserDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH_USER);
            ptm.setString(1, "%" + search + "%");
            ptm.setString(2, "%" + search + "%");
            ptm.setString(3, "%" + search + "%");

            rs = ptm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String typeID = rs.getString("typeID");
                String roleID = rs.getString("roleID");
                String gender = rs.getString("gender");
                String phoneNumber = rs.getString("phone");
                String avatarUrl = rs.getString("avatarUrl");
                boolean status = rs.getBoolean("status");
                list.add(new UserDTO(userID, fullName, password, email, status, typeID, roleID, gender, phoneNumber, avatarUrl));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<ManagerDTO> searchManager(String search) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ManagerDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH_MANAGER);
            ptm.setString(1, "%" + search + "%");
            ptm.setString(2, "%" + search + "%");
            ptm.setString(3, "%" + search + "%");

            rs = ptm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String typeID = rs.getString("typeID");
                String roleID = rs.getString("roleID");
                String gender = rs.getString("gender");
                String phoneNumber = rs.getString("phone");
                String avatarUrl = rs.getString("avatarUrl");
                String orgID = rs.getString("orgID");
                boolean status = rs.getBoolean("status");
                list.add(new ManagerDTO(orgID, userID, fullName, password, email, status, typeID, roleID, gender, phoneNumber, avatarUrl));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean deleteUser(String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(DELETE_USER);
            ptm.setString(1, userID);
            if (ptm.executeUpdate() > 0) {
                check = true;
            } else {
                check = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public ArrayList<EventPost> getAllEventList() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = VIEW_EVENT_LIST_BY_USER;
        boolean status = true;
        String statusTypeID = "AP";
        ArrayList<EventPost> eventList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setBoolean(1, status);
                ptm.setString(2, statusTypeID);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String takePlaceDate = rs.getString("takePlaceDate");
                    long millis = System.currentTimeMillis();
                    Date today = new Date(millis);
                    if (sdf.parse(takePlaceDate).before(today) == false) {
                        String eventID = rs.getString("eventID");
                        String orgID = rs.getString("orgID");
                        String createDate = rs.getString("createDate");
                        String content = rs.getString("content");
                        String title = rs.getString("title");
                        String location = rs.getString("location");
                        String imgUrl = rs.getString("imgUrl");
                        String eventTypeID = rs.getString("eventTypeID");
                        int numberOfView = rs.getInt("numberOfView");
                        String speaker = rs.getString("speaker");
                        String summary = rs.getString("summary");
                        String statusTypeName = rs.getString("statusTypeName");
                        String eventTypeName = rs.getString("eventTypeName");
                        String locationName = rs.getString("locationName");
                        String approvalDes = rs.getString("approvalDes");

                        EventPost eventPostInfo = new EventPost(takePlaceDate, location, eventTypeID, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, eventID, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);

                        eventList.add(eventPostInfo);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return eventList;
    }

    public ArrayList<EventPost> searchEventWithoutMark(String search) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = SEARCH_EVENT_WITHOUT_MARK;
        boolean status = true;
        String statusTypeID = "AP";
        ArrayList<EventPost> eventList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();

                while (rs.next()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String takePlaceDate = rs.getString("takePlaceDate");
                    long millis = System.currentTimeMillis();
                    Date today = new Date(millis);
                    if (sdf.parse(takePlaceDate).before(today) == false) {
                        String eventID = rs.getString("eventID");
                        String orgID = rs.getString("orgID");
                        String createDate = rs.getString("createDate");
                        String content = rs.getString("content");
                        String title = rs.getString("title");
                        String location = rs.getString("location");
                        String imgUrl = rs.getString("imgUrl");
                        String eventTypeID = rs.getString("eventTypeID");
                        int numberOfView = rs.getInt("numberOfView");
                        String speaker = rs.getString("speaker");
                        String summary = rs.getString("summary");
                        String statusTypeName = rs.getString("statusTypeName");
                        String eventTypeName = rs.getString("eventTypeName");
                        String locationName = rs.getString("locationName");
                        String approvalDes = rs.getString("approvalDes");

                        EventPost eventPostInfo = new EventPost(takePlaceDate, location, eventTypeID, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, eventID, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);

                        eventList.add(eventPostInfo);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return eventList;
    }
    
    public ArrayList<EventPost> getEventByTpye(String type) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_EVENT_BY_EVENT_TYPE;
        boolean status = true;
        String statusTypeID = "AP";
        ArrayList<EventPost> eventList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, type);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String takePlaceDate = rs.getString("takePlaceDate");
                    long millis = System.currentTimeMillis();
                    Date today = new Date(millis);
                    if (sdf.parse(takePlaceDate).before(today) == false) {
                        String eventID = rs.getString("eventID");
                        String orgID = rs.getString("orgID");
                        String createDate = rs.getString("createDate");
                        String content = rs.getString("content");
                        String title = rs.getString("title");
                        String location = rs.getString("location");
                        String imgUrl = rs.getString("imgUrl");
                        int numberOfView = rs.getInt("numberOfView");
                        String speaker = rs.getString("speaker");
                        String summary = rs.getString("summary");
                        String statusTypeName = rs.getString("statusTypeName");
                        String eventTypeName = rs.getString("eventTypeName");
                        String locationName = rs.getString("locationName");
                        String approvalDes = rs.getString("approvalDes");

                        EventPost eventPostInfo = new EventPost(takePlaceDate, location, type, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, eventID, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);

                        eventList.add(eventPostInfo);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return eventList;
    }

    public ArrayList<EventPost> searchEvent(String search) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = SEARCH_EVENT;
        boolean status = true;
        String statusTypeID = "AP";
        ArrayList<EventPost> eventList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();

                while (rs.next()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String takePlaceDate = rs.getString("takePlaceDate");
                    long millis = System.currentTimeMillis();
                    Date today = new Date(millis);
                    if (sdf.parse(takePlaceDate).before(today) == false) {
                        String eventID = rs.getString("eventID");
                        String orgID = rs.getString("orgID");
                        String createDate = rs.getString("createDate");
                        String content = rs.getString("content");
                        String title = rs.getString("title");
                        String location = rs.getString("location");
                        String imgUrl = rs.getString("imgUrl");
                        String eventTypeID = rs.getString("eventTypeID");
                        int numberOfView = rs.getInt("numberOfView");
                        String speaker = rs.getString("speaker");
                        String summary = rs.getString("summary");
                        String statusTypeName = rs.getString("statusTypeName");
                        String eventTypeName = rs.getString("eventTypeName");
                        String locationName = rs.getString("locationName");
                        String approvalDes = rs.getString("approvalDes");

                        EventPost eventPostInfo = new EventPost(takePlaceDate, location, eventTypeID, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, eventID, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);

                        eventList.add(eventPostInfo);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return eventList;
    }

    public EventPost getAnEvent(String eventID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = VIEW_EVENT_DETAIL_BY_USER;
        EventPost event = new EventPost();
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, eventID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String takePlaceDate = rs.getString("takePlaceDate");
                    String orgID = rs.getString("orgID");
                    String createDate = rs.getString("createDate");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    String location = rs.getString("location");
                    String imgUrl = rs.getString("imgUrl");
                    String eventTypeID = rs.getString("eventTypeID");
                    int numberOfView = rs.getInt("numberOfView");
                    String speaker = rs.getString("speaker");
                    String summary = rs.getString("summary");
                    String statusTypeName = rs.getString("statusTypeName");
                    String eventTypeName = rs.getString("eventTypeName");
                    String locationName = rs.getString("locationName");
                    String approvalDes = rs.getString("approvalDes");
                    boolean status = true;
                    String statusTypeID = "AP";

                    event = new EventPost(takePlaceDate, location, eventTypeID, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, eventID, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return event;
    }

    public void countNumberOfView(EventPost event) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ptm = null;
        String sql = COUNT_NUMBER_OF_VIEW;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);

                ptm.setInt(1, event.getNumberOfView());
                ptm.setString(2, event.getId());
                ptm.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList<OrganizationDTO> getAllOrganization() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ArrayList<OrganizationDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GET_ALL_ORGANIZATION);
            rs = ptm.executeQuery();
            while (rs.next()) {
                String orgID = rs.getString("orgID");
                String orgName = rs.getString("orgName");
                String createDate = rs.getString("createDate");
                String description = rs.getString("description");
                String imgUrl = rs.getString("imgUrl");
                boolean status = rs.getBoolean("status");

                list.add(new OrganizationDTO(orgID, orgName, createDate, description, imgUrl, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public ArrayList<EventPost> getInfoForHomePage() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_ALL_EVENT_INFO_FOR_USER_HOMEPAGE;
        boolean status = true;
        String statusTypeID = "AP";
        ArrayList<EventPost> eventList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setBoolean(1, status);
                ptm.setString(2, statusTypeID);
                rs = ptm.executeQuery();

                while (rs.next()) {

                    String takePlaceDate = rs.getString("takePlaceDate");
                    String eventID = rs.getString("eventID");
                    String orgID = rs.getString("org_ID");
                    String createDate = rs.getString("evt_CreateDate");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    String location = rs.getString("location");
                    String imgUrlEvent = rs.getString("evt_Img");
                    String eventTypeID = rs.getString("evt_TypeID");
                    int numberOfView = rs.getInt("numberOfView");
                    String speaker = rs.getString("speaker");
                    String summary = rs.getString("summary");
                    String statusTypeName = rs.getString("statusTypeName");
                    String eventTypeName = rs.getString("eventTypeName");
                    String locationName = rs.getString("locationName");
                    String approvalDes = rs.getString("approvalDes");
                    String imgURLCLB = rs.getString("org_Img");
                    String clbName = rs.getString("org_Name");
                    String clbDes = rs.getString("org_Description");

                    EventPost eventPostInfo = new EventPost(takePlaceDate, location, eventTypeID, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, imgURLCLB, clbName, clbDes, eventID, orgID, title, content, createDate, imgUrlEvent, numberOfView, summary, status);

                    eventList.add(eventPostInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return eventList;
    }

    public ArrayList<EventPost> getEventByDate(String takePlaceDate) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_EVENT_BY_DATE;
        boolean status = true;
        String statusTypeID = "AP";
        ArrayList<EventPost> eventList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setBoolean(1, status);
                ptm.setString(2, statusTypeID);
                ptm.setString(3, takePlaceDate);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    long millis = System.currentTimeMillis();
                    Date today = new Date(millis);
                    if (sdf.parse(takePlaceDate).before(today) == false) {
                        String eventID = rs.getString("eventID");
                        String orgID = rs.getString("org_ID");
                        String createDate = rs.getString("evt_CreateDate");
                        String content = rs.getString("content");
                        String title = rs.getString("title");
                        String location = rs.getString("location");
                        String imgUrlEvent = rs.getString("evt_Img");
                        String eventTypeID = rs.getString("evt_TypeID");
                        int numberOfView = rs.getInt("numberOfView");
                        String speaker = rs.getString("speaker");
                        String summary = rs.getString("summary");
                        String statusTypeName = rs.getString("statusTypeName");
                        String eventTypeName = rs.getString("eventTypeName");
                        String locationName = rs.getString("locationName");
                        String approvalDes = rs.getString("approvalDes");
                        String imgURLCLB = rs.getString("org_Img");
                        String clbName = rs.getString("org_Name");
                        String clbDes = rs.getString("org_Description");

                        EventPost eventPostInfo = new EventPost(takePlaceDate, location, eventTypeID, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, imgURLCLB, clbName, clbDes, eventID, orgID, title, content, createDate, imgUrlEvent, numberOfView, summary, status);

                        eventList.add(eventPostInfo);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return eventList;
    }

    public ArrayList<EventPost> getNewestEvent() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_NEWEST_EVENT;
        boolean status = true;
        String statusTypeID = "AP";
        ArrayList<EventPost> eventList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setBoolean(1, status);
                ptm.setString(2, statusTypeID);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String takePlaceDate = rs.getString("takePlaceDate");
                    long millis = System.currentTimeMillis();
                    Date today = new Date(millis);
                    if (sdf.parse(takePlaceDate).before(today) == false) {

                        String eventID = rs.getString("eventID");
                        String orgID = rs.getString("org_ID");
                        String createDate = rs.getString("evt_CreateDate");
                        String content = rs.getString("content");
                        String title = rs.getString("title");
                        String location = rs.getString("location");
                        String imgUrlEvent = rs.getString("evt_Img");
                        String eventTypeID = rs.getString("evt_TypeID");
                        int numberOfView = rs.getInt("numberOfView");
                        String speaker = rs.getString("speaker");
                        String summary = rs.getString("summary");
                        String statusTypeName = rs.getString("statusTypeName");
                        String eventTypeName = rs.getString("eventTypeName");
                        String locationName = rs.getString("locationName");
                        String approvalDes = rs.getString("approvalDes");
                        String imgURLCLB = rs.getString("org_Img");
                        String clbName = rs.getString("org_Name");
                        String clbDes = rs.getString("org_Description");

                        EventPost eventPostInfo = new EventPost(takePlaceDate, location, eventTypeID, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, imgURLCLB, clbName, clbDes, eventID, orgID, title, content, createDate, imgUrlEvent, numberOfView, summary, status);

                        eventList.add(eventPostInfo);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return eventList;
    }

    public void participantsUser(String userID, String eventID) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = PARTICIPANTS;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, eventID);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ParticipantsDTO getParticipants(String userID, String eventID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ParticipantsDTO participants = null;
        try {
            String sql = GET_PARTICIPANTS;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, eventID);
            rs = ptm.executeQuery();
            if (rs.next()) {

                participants = new ParticipantsDTO(userID, eventID, true);
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return participants;

    }

    public OrganizationDTO getAClubInfo(String orgID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        OrganizationDTO organization = null;
        try {
            String sql = GET_INFO_CLUB;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, orgID);
            rs = ptm.executeQuery();

            if (rs.next()) {
                String description = rs.getString("description");
                String orgName = rs.getString("orgName");
                String createDate = rs.getString("createDate");
                boolean status = rs.getBoolean("status");
                String imgURL = rs.getString("imgURL");
                String email = rs.getString("email");

                organization = new OrganizationDTO(orgID, orgName, createDate, description, imgURL, status, email);
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return organization;

    }

    public ArrayList<EventPost> getEventPostForClubProfile(String orgID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_ALL_EVENT_INFO_FOR_CLUB_PROFILE;
        boolean status = true;
        String statusTypeID = "AP";
        ArrayList<EventPost> eventList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setBoolean(1, status);
                ptm.setString(2, statusTypeID);
                ptm.setString(3, orgID);
                rs = ptm.executeQuery();

                while (rs.next()) {

                    String takePlaceDate = rs.getString("takePlaceDate");
                    String eventID = rs.getString("eventID");
                    String createDate = rs.getString("evt_CreateDate");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    String location = rs.getString("location");
                    String imgUrlEvent = rs.getString("evt_Img");
                    String eventTypeID = rs.getString("evt_TypeID");
                    int numberOfView = rs.getInt("numberOfView");
                    String speaker = rs.getString("speaker");
                    String summary = rs.getString("summary");
                    String statusTypeName = rs.getString("statusTypeName");
                    String eventTypeName = rs.getString("eventTypeName");
                    String locationName = rs.getString("locationName");
                    String approvalDes = rs.getString("approvalDes");
                    String imgURLCLB = rs.getString("org_Img");
                    String clbName = rs.getString("org_Name");
                    String clbDes = rs.getString("org_Description");

                    EventPost eventPostInfo = new EventPost(takePlaceDate, location, eventTypeID, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, imgURLCLB, clbName, clbDes, eventID, orgID, title, content, createDate, imgUrlEvent, numberOfView, summary, status);

                    eventList.add(eventPostInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return eventList;
    }

    public OrganizationFollowerDTO getUserFollowing(String userID, String orgID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        OrganizationFollowerDTO userFollow = null;
        try {
            String sql = CHECK_USER_FOLLOWING;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, orgID);
            rs = ptm.executeQuery();

            if (rs.next()) {

                userFollow = new OrganizationFollowerDTO(userID, orgID, true);
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return userFollow;

    }

    public OrganizationFollowerDTO getUserUnfollow(String userID, String orgID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        OrganizationFollowerDTO userFollow = null;
        try {
            String sql = CHECK_USER_UNFOLLOW;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, orgID);
            rs = ptm.executeQuery();

            if (rs.next()) {
                boolean status = rs.getBoolean("status");

                userFollow = new OrganizationFollowerDTO(userID, orgID, status);
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return userFollow;

    }

    public void followByUser(String userID, String orgID) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = FOLLOW_BY_USER;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, orgID);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void unfollowByUser(String userID, String orgID) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = UNFOLLOW_BY_USER;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, orgID);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void followAgainByUser(String userID, String orgID) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = FOLLOW_AGAIN_BY_USER;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, orgID);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList<BlogDTO> getAllBlog() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_ALL_BLOG;
        boolean status = true;
        ArrayList<BlogDTO> blogList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String blogID = rs.getString("blogID");
                    String orgID = rs.getString("org_ID");
                    String createDate = rs.getString("create_Date");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    int numberOfView = rs.getInt("numberOfView");
                    String summary = rs.getString("summary");
                    String imgURL = rs.getString("img_URL");
                    String orgName = rs.getString("orgName");

                    BlogDTO blogInfo = new BlogDTO(orgName, blogID, orgID, title, content, createDate, imgURL, numberOfView, summary, status);

                    blogList.add(blogInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return blogList;
    }

    public BlogDTO getBlogDetail(String blogID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_BLOG_DETAIL;
        boolean status = true;
        BlogDTO blogDetail = new BlogDTO();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, blogID);
                rs = ptm.executeQuery();

                while (rs.next()) {

                    String orgID = rs.getString("org_ID");
                    String createDate = rs.getString("create_Date");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    int numberOfView = rs.getInt("numberOfView");
                    String summary = rs.getString("summary");
                    String imgURL = rs.getString("img_URL");
                    String orgName = rs.getString("orgName");

                    blogDetail = new BlogDTO(orgName, blogID, orgID, title, content, createDate, imgURL, numberOfView, summary, status);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return blogDetail;
    }

    public void countBlogViewNumber(BlogDTO blog) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ptm = null;
        String sql = COUNT_BLOG_VIEW_NUMBER;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);

                ptm.setInt(1, blog.getNumberOfView());
                ptm.setString(2, blog.getId());
                ptm.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList<BlogDTO> getNewestBlog() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_NEWEST_BLOG;
        boolean status = true;
        ArrayList<BlogDTO> blogList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String blogID = rs.getString("blogID");
                    String orgID = rs.getString("org_ID");
                    String createDate = rs.getString("create_Date");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    int numberOfView = rs.getInt("numberOfView");
                    String summary = rs.getString("summary");
                    String imgURL = rs.getString("img_URL");
                    String orgName = rs.getString("orgName");

                    BlogDTO blogInfo = new BlogDTO(orgName, blogID, orgID, title, content, createDate, imgURL, numberOfView, summary, status);

                    blogList.add(blogInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return blogList;
    }

    public ArrayList<CommentSectionDTO> getComment(String eventID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_COMMENT;
        boolean status = true;
        ArrayList<CommentSectionDTO> commentList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, eventID);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    int commentID = rs.getInt("commentID");
                    int replyID = rs.getInt("replyID");
                    String time = rs.getString("time");
                    String content = rs.getString("content");
                    String userID = rs.getString("id");
                    String fullName = rs.getString("name");
                    String img = rs.getString("img");

                    CommentSectionDTO comment = new CommentSectionDTO(commentID, replyID, status, time, content, userID, eventID, fullName, img);

                    commentList.add(comment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return commentList;
    }

    public void commentByUser(Date time, String content, String userID, String eventID) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = COMMENT;
        boolean status = true;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setBoolean(1, status);
            ptm.setDate(2, time);
            ptm.setString(3, content);
            ptm.setString(4, userID);
            ptm.setString(5, eventID);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void replyByUser(int replyID, Date time, String content, String userID, String eventID) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = REPLY;
        boolean status = true;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setInt(1, replyID);
            ptm.setBoolean(2, status);
            ptm.setDate(3, time);
            ptm.setString(4, content);
            ptm.setString(5, userID);
            ptm.setString(6, eventID);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList<NotificationDTO> getNotification(String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = NOTIFICATION;
        ArrayList<NotificationDTO> notiList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String evtID = rs.getString("evtID");
                    String evtContent = rs.getString("evtContent");
                    String notiDate = rs.getString("notiDate");
                    String notiContent = rs.getString("notiContent");
                    String orgID = rs.getString("orgID");
                    String orgImg = rs.getString("orgImg");

                    NotificationDTO noti = new NotificationDTO(userID, evtID, evtContent, notiDate, notiContent, orgID, orgImg);

                    notiList.add(noti);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return notiList;
    }

    public Date getTomorow() {
        long millis = System.currentTimeMillis();
        Date today = new Date(millis);
        long nextDayMilliSeconds = today.getTime() + ONE_DAY_MILLI_SECONDS;
        Date nextDate = new Date(nextDayMilliSeconds);
        return nextDate;
    }

    public Date getTheDayAfterTomorow() {
        long millis = System.currentTimeMillis();
        Date today = new Date(millis);
        long nextDayMilliSeconds = today.getTime() + ONE_DAY_MILLI_SECONDS + ONE_DAY_MILLI_SECONDS;
        Date theDayAfterTomorow = new Date(nextDayMilliSeconds);
        return theDayAfterTomorow;
    }

    public static void main(String[] args) throws SQLException, ParseException, Exception {

        ArrayList<EventPost> e = new ArrayList<EventPost>();
        UserDAO dao = new UserDAO();
        e = dao.getEventByTpye("2");
        System.out.println(e);

    }
}
