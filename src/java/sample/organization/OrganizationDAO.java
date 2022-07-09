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
import java.util.ArrayList;
import java.util.List;
import sample.util.DBUtils;

/**
 *
 * @author light
 */
public class OrganizationDAO {

    private static final String GET_ALL_ORGANIZATION = "SELECT orgID, orgName, createDate, description, imgUrl, status\n"
            + "FROM tblOrgPage";

    private static final String SEARCH_ORGANIZATION = "SELECT  orgID, orgName, createDate, description, imgUrl, status\n"
            + "             FROM tblOrgPage \n"
            + "             WHERE dbo.ufn_removeMark(orgName) like ? or orgName like ? or orgID like ?";

    private static final String CREATE_ORGANIZATION = "INSERT INTO tblOrgPage (orgID, status, orgName, createDate, description, imgUrl)\n"
            + "VALUES(?, ?, ?, ?, ?, ?)";

    private static final String GET_ID_ORGANIZATION = "SELECT orgID, orgName, createDate, description, imgUrl, status\n"
            + "FROM tblOrgPage\n"
            + "WHERE orgID = ?";
    private static final String DELETE_ORGANIZATION = "UPDATE tblOrgPage SET status = '0' WHERE orgID = ?";

    private static final String UPDATE_ORGANIZATION = "UPDATE tblOrgPage "
            + "SET orgID = ?, orgName = ?, description = ?, imgUrl = ?, status = ? "
            + "WHERE orgID = ?";

    public List<OrganizationDTO> getAllOrganization() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<OrganizationDTO> list = new ArrayList<>();
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

    public List<OrganizationDTO> searchOrganization(String search) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<OrganizationDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH_ORGANIZATION);
            ptm.setString(1, "%" + search + "%");
            ptm.setString(2, "%" + search + "%");
            ptm.setString(3, "%" + search + "%");
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

    public OrganizationDTO getOrganization(String orgID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        OrganizationDTO org = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ID_ORGANIZATION);
                ptm.setString(1, orgID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String orgName = rs.getString("orgName");
                    String createDate = rs.getString("createDate");
                    String description = rs.getString("description");
                    String imgUrl = rs.getString("imgUrl");
                    boolean status = rs.getBoolean("status");
                    org = new OrganizationDTO(orgID, orgName, createDate, description, imgUrl, status);
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
        return org;
    }

    public boolean createOrg(OrganizationDTO org) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_ORGANIZATION);
                ptm.setString(1, org.getOrgID());
                ptm.setBoolean(2, org.isStatus());
                ptm.setString(3, org.getOrgName());
                Date importDate = Date.valueOf(org.getCreateDate());
                ptm.setDate(4, importDate);
                ptm.setString(5, org.getDescription());
                ptm.setString(6, org.getImgUrl());
                if (ptm.executeUpdate() > 0) {
                    check = true;
                } else {
                    check = false;
                }
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

    public boolean deleteOrg(String orgID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_ORGANIZATION);
                ptm.setString(1, orgID);
                if (ptm.executeUpdate() > 0) {
                    check = true;
                } else {
                    check = false;
                }
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

    public boolean updateOrg(OrganizationDTO org, String orgID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_ORGANIZATION);
                ptm.setString(1, org.getOrgID());
                ptm.setString(2, org.getOrgName());
                ptm.setString(3, org.getDescription());
                ptm.setString(4, org.getImgUrl());
                ptm.setBoolean(5, org.isStatus());
                ptm.setString(6, orgID);

                if (ptm.executeUpdate() > 0) {
                    check = true;
                } else {
                    check = false;
                }
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

}
