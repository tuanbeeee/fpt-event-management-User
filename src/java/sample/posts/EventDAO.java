/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.posts;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sample.posts.EventLocation;
import sample.posts.EventPost;
import sample.posts.EventType;
import sample.util.DBUtils;

/**
 *
 * @author tvfep
 */
public class EventDAO {

    private static final String GET_ALL_EVENT_POST = "SELECT eventID, orgID, createDate, takePlaceDate, content, title, location, imgUrl, tblEventPost.eventTypeID, numberOfView, speaker, summary, \n"
            + "			status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes\n"
            + "            FROM tblEventPost, tblEventType, tblLocation, tblStatusType\n"
            + "            WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and tblEventPost.statusTypeID = tblStatusType.statusTypeID\n";

    private static final String GET_ALL_EVENT_BY_TITLE = "SELECT eventID, orgID, createDate, takePlaceDate, content, title, location, imgUrl, tblEventPost.eventTypeID, numberOfView, speaker, summary, \n"
            + "            status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes\n"
            + "            FROM tblEventPost, tblEventType, tblLocation, tblStatusType\n"
            + "            WHERE (dbo.ufn_removeMark(tblEventPost.title) LIKE ? or title LIKE ?)\n"
            + "            and tblEventPost.eventTypeID = tblEventType.eventTypeID and \n"
            + "            tblEventPost.location = tblLocation.locationID and tblEventPost.statusTypeID = tblStatusType.statusTypeID";

    private static final String GET_AN_EVENT_BY_ID = "SELECT eventID, orgID, createDate, takePlaceDate, content, title, location, imgUrl, tblEventPost.eventTypeID, numberOfView, speaker, summary, \n"
            + "            status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes\n"
            + "            FROM tblEventPost, tblEventType, tblLocation, tblStatusType\n"
            + "            WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID \n"
            + "            and tblEventPost.statusTypeID = tblStatusType.statusTypeID and tblEventPost.eventID LIKE ?\n";

    private static final String ADD_AN_EVENT = "INSERT INTO tblEventPost (eventID, status, createDate, takePlaceDate, content, title, location, imgUrl, eventTypeID, numberOfView, speaker, summary) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String CHECK_EVENT_DUPLICATE = "SELECT eventID FROM tblEventPost where eventID = ?";

    private static final String UPDATE_AN_EVENT = "UPDATE [dbo].[tblEventPost]\n"
            + "   SET [status] = ?\n"
            + "      ,[createDate] = ?\n"
            + "      ,[takePlaceDate] = ?\n"
            + "      ,[content] = ?\n"
            + "      ,[title] = ?\n"
            + "      ,[location] = ?\n"
            + "      ,[imgUrl] = ?\n"
            + "      ,[eventTypeID] = ?\n"
            + "      ,[numberOfView] = ?\n"
            + "      ,[speaker] = ?\n"
            + "      ,[summary] = ?\n"
            + " WHERE eventID = ?";

    private static final String GET_ALL_EVENT_TYPE = "SELECT eventTypeID, eventTypeName\n"
            + "FROM tblEventType";

    private static final String GET_ALL_EVENT_LOCATION = "SELECT locationID, locationName\n"
            + "FROM tblLocation";

    private static final String GET_NUMBER_OF_PARTICIPANTS = "SELECT COUNT(userID) as total\n"
            + "  FROM tblParticipants\n"
            + "  Where eventID = ?";

    private static final String GET_ALL_EVENT_BY_ORG = "SELECT eventID, orgID, createDate, takePlaceDate, content, title, location, imgUrl, tblEventPost.eventTypeID,\n"
            + "numberOfView, speaker, summary, status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes\n"
            + "FROM tblEventPost, tblEventType, tblLocation, tblStatusType\n"
            + "WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID\n"
            + "and tblEventPost.statusTypeID = tblStatusType.statusTypeID and tblEventPost.orgID = ?";

    public List<EventPost> getAllEvent() throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<EventPost> listEvent = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL_EVENT_POST);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("eventID");
                String orgID = rs.getString("orgID");
                String createDate = rs.getString("createDate");
                String takePlaceDate = rs.getString("takePlaceDate");
                String content = rs.getString("content");
                String title = rs.getString("title");
                String location = rs.getString("location");
                String imgUrl = rs.getString("imgUrl");
                String eventType = rs.getString("eventTypeID");
                int numberOfView = rs.getInt("numberOfView");
                String speaker = rs.getString("speaker");
                String summary = rs.getString("summary");
                Boolean status = rs.getBoolean("status");
                String eventTypeName = rs.getString("eventTypeName");
                String locationName = rs.getString("locationName");
                String statusTypeID = rs.getString("statusTypeID");
                String statusTypeName = rs.getString("statusTypeName");
                String approvalDes = rs.getString("approvalDes");

                EventPost event = new EventPost(takePlaceDate, location, eventType, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, id, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);
                listEvent.add(event);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listEvent;

    }

    public List<EventPost> getListEventByTitle(String search) throws SQLException {
        List<EventPost> listEvent = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_ALL_EVENT_BY_TITLE);
                ps.setString(1, "%" + search + "%");
                ps.setString(2, "%" + search + "%");

                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("eventID");
                    String orgID = rs.getString("orgID");
                    String createDate = rs.getString("createDate");
                    String takePlaceDate = rs.getString("takePlaceDate");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    String location = rs.getString("location");
                    String imgUrl = rs.getString("imgUrl");
                    String eventType = rs.getString("eventTypeID");
                    int numberOfView = rs.getInt("numberOfView");
                    String speaker = rs.getString("speaker");
                    String summary = rs.getString("summary");
                    Boolean status = rs.getBoolean("status");
                    String eventTypeName = rs.getString("eventTypeName");
                    String locationName = rs.getString("locationName");
                    String statusTypeID = rs.getString("statusTypeID");
                    String statusTypeName = rs.getString("statusTypeName");
                    String approvalDes = rs.getString("approvalDes");

                    EventPost event = new EventPost(takePlaceDate, location, eventType, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, id, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);
                    listEvent.add(event);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listEvent;
    }

    public EventPost getAnEventByID(String eventID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        EventPost event = new EventPost();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_AN_EVENT_BY_ID);
                ps.setString(1, "%" + eventID + "%");

                rs = ps.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("eventID");
                    String orgID = rs.getString("orgID");
                    String createDate = rs.getString("createDate");
                    String takePlaceDate = rs.getString("takePlaceDate");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    String location = rs.getString("location");
                    String imgUrl = rs.getString("imgUrl");
                    String eventType = rs.getString("eventTypeID");
                    int numberOfView = rs.getInt("numberOfView");
                    String speaker = rs.getString("speaker");
                    String summary = rs.getString("summary");
                    Boolean status = rs.getBoolean("status");
                    String eventTypeName = rs.getString("eventTypeName");
                    String locationName = rs.getString("locationName");
                    String statusTypeID = rs.getString("statusTypeID");
                    String statusTypeName = rs.getString("statusTypeName");
                    String approvalDes = rs.getString("approvalDes");

                    event = new EventPost(takePlaceDate, location, eventType, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, id, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return event;
    }

    public boolean checkEventIDDuplicate(String eventID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = true;
        String checkID = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_EVENT_DUPLICATE);
                ps.setString(1, eventID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    checkID = rs.getString("eventID");
                }
                if (checkID == null) {
                    check = false;
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean createAnEvent(EventPost event) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ADD_AN_EVENT);
                ps.setString(1, event.getId());
                ps.setString(2, null);
                ps.setDate(3, Date.valueOf(event.getCreateDate()));
                ps.setDate(4, Date.valueOf(event.getTakePlaceDate()));
                ps.setString(5, event.getContent());
                ps.setString(6, event.getTitle());
                ps.setString(7, event.getLocation());
                ps.setString(8, event.getImgUrl());
                ps.setString(9, event.getEventType());
                ps.setInt(10, event.getNumberOfView());
                ps.setString(11, event.getSpeaker());
                ps.setString(12, event.getSummary());

                if (ps.executeUpdate() > 0) {
                    check = true;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateAnEvent(EventPost event) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_AN_EVENT);
                ps.setBoolean(1, event.isStatus());
                ps.setDate(2, Date.valueOf(event.getCreateDate()));
                ps.setDate(3, Date.valueOf(event.getTakePlaceDate()));
                ps.setString(4, event.getContent());
                ps.setString(5, event.getTitle());
                ps.setString(6, event.getLocation());
                ps.setString(7, event.getImgUrl());
                ps.setString(8, event.getEventType());
                ps.setInt(9, event.getNumberOfView());
                ps.setString(10, event.getSpeaker());
                ps.setString(11, event.getSummary());
                ps.setString(12, event.getId());

                int checkUpdate = ps.executeUpdate();
                if (checkUpdate > 0) {
                    check = true;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<EventType> getAllEventType() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<EventType> listTypes = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL_EVENT_TYPE);
            rs = ps.executeQuery();
            while (rs.next()) {
                String eventTypeID = rs.getString("eventTypeID");
                String eventTypeName = rs.getString("eventTypeName");

                EventType evtType = new EventType(eventTypeID, eventTypeName);
                listTypes.add(evtType);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listTypes;
    }

    public List<EventLocation> getAllEventLocation() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<EventLocation> listLocations = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL_EVENT_LOCATION);
            rs = ps.executeQuery();
            while (rs.next()) {
                String locationID = rs.getString("locationID");
                String locationName = rs.getString("locationName");

                EventLocation evtLocation = new EventLocation(locationID, locationName);
                listLocations.add(evtLocation);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listLocations;
    }

    public int getNumberOfParticipants(String eventID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(GET_NUMBER_OF_PARTICIPANTS);
            ps.setString(1, eventID);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return total;
    }

}
