/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.comment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sample.util.DBUtils;

/**
 *
 * @author Tuan Be
 */
public class CommentSectionDAO {

    private static final String GET_COMMENT = "SELECT commentID, replyID, tblCommentSections.status as sts, time, content, tblCommentSections.userID as id, tblUsers.fullName as name, tblUsers.avatarUrl as img , eventID FROM tblCommentSections, tblUsers WHERE tblUsers.userID = tblCommentSections.userID AND eventID = ? AND tblCommentSections.status = '1'";

    private static final String COMMENT = "INSERT INTO tblCommentSections (status ,time, content, userID, eventID) VALUES (? ,?, ?, ?, ?)";

    private static final String REPLY = "INSERT INTO tblCommentSections (replyID, status ,time, content, userID, eventID) VALUES (?, ? ,?, ?, ?, ?)";

    private static final String DELETE_COMMENT = "UPDATE tblCommentSections SET status = '0' WHERE userID = ? AND commentID = ?";

    public void deleteComment(String userID, String cmtID) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = DELETE_COMMENT;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userID);
            ptm.setString(2, cmtID);
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
}
