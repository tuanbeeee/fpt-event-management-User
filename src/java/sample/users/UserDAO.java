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

    private static final String UPDATE_USER_PROFILE = "UPDATE tblUsers SET fullName = ?, email=?, typeID =?, gender=?, phone=?, avatarUrl=? WHERE userID=?";

    private static final String CHECK_UPDATE_EMAIL_EXIST = "SELECT userID, fullName, password, email, status, typeID, roleID, gender, phone, avatarUrl FROM tblUsers WHERE email = ? and userID != ?";

    private static final String GET_PARTICIPANTS = "SELECT userID, eventID, status FROM tblParticipants WHERE userID = ? AND eventID = ? AND status = '1'";

    private static final String GET_UNPARTICIPANTS = "SELECT userID, eventID, status FROM tblParticipants WHERE userID = ? AND eventID = ? AND status = '0'";

    private static final String PARTICIPANTS = "INSERT INTO tblParticipants (userID, eventID, status) VALUES (?, ?, '1')";

    private static final String GET_PARTICIPANTS_LIST = "SELECT userID, eventID, status FROM tblParticipants WHERE eventID = ? AND status = 'true'";

    private static final String PARTICIPANT_AGAIN_BY_USER = "UPDATE tblParticipants SET status = '1' WHERE userID = ? AND eventID = ?";

    private static final String UNPARTICIPANT_BY_USER = "UPDATE tblParticipants SET status = '0' WHERE userID = ? AND eventID = ?";

    private static final String CHECK_USER_FOLLOWING = "SELECT userID, orgID, status FROM tblOrg_Follower WHERE userID = ? AND orgID = ? AND status = '1'";

    private static final String CHECK_USER_UNFOLLOW = "SELECT userID, orgID, status FROM tblOrg_Follower WHERE userID = ? AND orgID = ? AND status = '0'";

    private static final String FOLLOW_BY_USER = "INSERT INTO tblOrg_Follower (userID, orgID, status) VALUES (?, ?, '1')";

    private static final String UNFOLLOW_BY_USER = "UPDATE tblOrg_Follower SET status = '0' WHERE userID = ? AND orgID = ?";

    private static final String FOLLOW_AGAIN_BY_USER = "UPDATE tblOrg_Follower SET status = '1' WHERE userID = ? AND orgID = ?";

    private static final String CHANGE_PASSWORD = "UPDATE tblUsers SET password = ? WHERE userID = ?";

    private static final String GET_PASS = "SELECT password FROM tblUsers WHERE userID = ?";

    public void participantAgainByUser(String userID, String event) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = PARTICIPANT_AGAIN_BY_USER;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, event);
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

    public void unparticipantByUser(String userID, String event) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = UNPARTICIPANT_BY_USER;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, event);
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

    public String getPass(String userID) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String passwordDB = null;

        try {
            String sql = GET_PASS;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            rs = ptm.executeQuery();

            while (rs.next()) {
                passwordDB = rs.getString("password");
                if (passwordDB != null) {
                    return passwordDB;
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

        return null;
    }

    public void changePassword(String userID, String pass) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = CHANGE_PASSWORD;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, pass);
            ptm.setString(2, userID);
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
                if (userID.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("US") && status.equals("1")) {
                    return "US";
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

    public ParticipantsDTO getUnparticipants(String userID, String eventID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ParticipantsDTO participants = null;
        try {
            String sql = GET_UNPARTICIPANTS;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, eventID);
            rs = ptm.executeQuery();
            if (rs.next()) {

                participants = new ParticipantsDTO(userID, eventID, false);
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

    public ArrayList<ParticipantsDTO> getParticipantsList(String eventID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ParticipantsDTO participants = null;
        ArrayList<ParticipantsDTO> list = new ArrayList<>();
        try {
            String sql = GET_PARTICIPANTS_LIST;
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, eventID);
            rs = ptm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");

                participants = new ParticipantsDTO(userID, eventID, true);
                list.add(participants);
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
        return list;

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

        ArrayList<ParticipantsDTO> e = new ArrayList<ParticipantsDTO>();
        UserDAO dao = new UserDAO();
        e = dao.getParticipantsList("EVT1");
        System.out.println(e);

    }
}
