/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sample.util.DBUtils;

/**
 *
 * @author Tuan Be
 */
public class UserFeedbackDAO {

    private static final String FEEDBACK_BY_USER = "INSERT INTO tblUser_Feedback (userID, eventID, content) VALUES (?, ?, ?)";

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
}
