/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.organization;

/**
 *
 * @author Tuan Be
 */
public class OrganizationFollowerDTO {
    
    private String userID;
    private String orgID;
    private boolean status;

    public OrganizationFollowerDTO() {
        this.userID = "";
        this.orgID = "";
        this.status = false;
    }

    public OrganizationFollowerDTO(String userID, String orgID, boolean status) {
        this.userID = userID;
        this.orgID = orgID;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrganizationFollowerDTO{" + "userID=" + userID + ", orgID=" + orgID + ", status=" + status + '}';
    }

    
    
    
    
}
