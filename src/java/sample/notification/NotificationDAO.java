/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sample.util.DBUtils;

/**
 *
 * @author Tuan Be
 */
public class NotificationDAO {

    private static final String NOTIFICATION = "SELECT userID, tblUserNotification.eventID AS evtID, tblEventPost.content AS evtContent, notiDate, tblUserNotification.content AS notiContent, tblEventPost.orgID AS orgID, tblOrgPage.imgUrl AS orgImg FROM tblUserNotification, tblEventPost, tblOrgPage WHERE tblEventPost.orgID = tblOrgPage.orgID AND tblEventPost.eventID = tblUserNotification.eventID AND userID = ?";

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

}
