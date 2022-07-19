/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.organization;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import sample.posts.EventPost;
import sample.users.ManagerDTO;
import sample.util.DBUtils;

/**
 *
 * @author light
 */
public class OrganizationDAO {

    private static final String GET_ALL_ORG = "SELECT orgID, orgName FROM tblOrgPage WHERE status = '1'";

    private static final String GET_ALL_ORGANIZATION = "SELECT orgID, orgName, createDate, description, imgUrl, status\n"
            + "FROM tblOrgPage WHERE statusTypeID ='AP' AND status='True'";

    private static final String GET_INFO_CLUB = "SELECT orgID, status, orgName, createDate, description, imgURL, email FROM tblOrgPage WHERE orgID =? AND status = '1' AND statusTypeID = 'AP'";

    private static final String CHECK_ORGID_EXIST = "SELECT orgID, status, orgName, createDate, description, imgUrl, email, statusTypeID\n"
            + "FROM tblOrgPage \n"
            + "WHERE orgID = ? AND statusTypeID != 'DE' AND status = '1'";

    private static final String CHECK_ORG_EMAIL_EXIST = "SELECT orgID, status, orgName, createDate, description, imgUrl, email, statusTypeID\n"
            + "FROM tblOrgPage \n"
            + "WHERE email = ? AND statusTypeID != 'DE' AND status = '1' ";

    private static final String SIGN_UP_BY_ORG = "INSERT INTO tblOrgPage (orgID, status, orgName, createDate, description, imgUrl, email, statusTypeID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SEARCH_CLUB_BY_USER = "SELECT orgID, orgName, createDate, description, imgUrl, status\n"
            + "FROM tblOrgPage WHERE statusTypeID ='AP' AND status= 1 AND orgName LIKE ?";

    private static final String SEARCH_CLUB_WITHOUTMARK_BY_USER = "SELECT orgID, orgName, createDate, description, imgUrl, status\n"
            + "FROM tblOrgPage WHERE statusTypeID ='AP' AND status= 1 AND dbo.ufn_removeMark(orgName) LIKE ?";

    public ArrayList<OrganizationDTO> searchOrganization(String search) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ArrayList<OrganizationDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH_CLUB_BY_USER);
            ptm.setString(1, "%" + search + "%");
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

    public ArrayList<OrganizationDTO> searchOrganizationWithoutMark(String search) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ArrayList<OrganizationDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH_CLUB_WITHOUTMARK_BY_USER);
            ptm.setString(1, "%" + search + "%");
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

    public boolean checkInputOrgID(String userID) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{3,16}$");
        if (p.matcher(userID).find()) {
            return true;
        } else {
            return false;
        }
    }

}
