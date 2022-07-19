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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sample.eventtype.EventTypeDTO;
import sample.posts.EventLocation;
import sample.posts.EventPost;
import sample.posts.EventType;
import sample.util.DBUtils;

/**
 *
 * @author tvfep
 */
public class EventDAO {

    private static final String VIEW_EVENT_LIST_BY_USER = "SELECT eventID, orgID, createDate, takePlaceDate, content, title, location, imgUrl, tblEventPost.eventTypeID, numberOfView, speaker, summary, \n"
            + "            tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes\n"
            + "            FROM tblEventPost, tblEventType, tblLocation, tblStatusType\n"
            + "            WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and\n"
            + "			tblEventPost.statusTypeID = tblStatusType.statusTypeID AND tblEventPost.status = ? AND tblStatusType.statusTypeID =?";

    private static final String VIEW_EVENT_DETAIL_BY_USER = "SELECT eventID, orgID, createDate, takePlaceDate, content, title, location, imgUrl, tblEventPost.eventTypeID, numberOfView, speaker, summary, tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes, participationLimit\n"
            + "                    FROM tblEventPost, tblEventType, tblLocation, tblStatusType\n"
            + "                    WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and\n"
            + "                    tblEventPost.statusTypeID = tblStatusType.statusTypeID AND eventID = ?";

    private static final String COUNT_NUMBER_OF_VIEW = "UPDATE tblEventPost SET  numberOfView = ? WHERE eventID = ?";

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

    private static final String GET_ALL_EVENT_INFO_FOR_CLUB_PROFILE = "SELECT eventID, tblEventPost.orgID as org_ID, tblEventPost.createDate as evt_CreateDate, takePlaceDate, content, title, location, tblEventPost.imgUrl as evt_Img, tblEventPost.eventTypeID as evt_TypeID, numberOfView, speaker, summary, tblEventPost.status, tblEventPost.statusTypeID, statusTypeName, eventTypeName, locationName, approvalDes, tblOrgPage.imgUrl as org_Img, tblOrgPage.orgName as org_Name, tblOrgPage.description as org_Description\n"
            + "            FROM tblEventPost, tblEventType, tblLocation, tblStatusType, tblOrgPage\n"
            + "            WHERE tblEventPost.eventTypeID = tblEventType.eventTypeID and tblEventPost.location = tblLocation.locationID and tblEventPost.statusTypeID = tblStatusType.statusTypeID and tblEventPost.orgID = tblOrgPage.orgID AND tblEventPost.status = ? AND tblStatusType.statusTypeID =? AND tblEventPost.orgID = ?";

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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String takePlaceDate = rs.getString("takePlaceDate");
                    long millis = System.currentTimeMillis();
                    Date today = new Date(millis);
                    if (sdf.parse(takePlaceDate).before(today) == false) {
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
                    int participationLimit = rs.getInt("participationLimit");
                    boolean status = true;
                    String statusTypeID = "AP";

                    event = new EventPost(takePlaceDate, location, eventTypeID, speaker, eventTypeName, locationName, statusTypeID, statusTypeName, approvalDes, eventID, orgID, title, content, createDate, imgUrl, numberOfView, summary, status, participationLimit);
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

}
