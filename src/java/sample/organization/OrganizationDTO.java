/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.organization;

/**
 *
 * @author light
 */
public class OrganizationDTO {

    private String orgID;
    private String orgName;
    private String createDate;
    private String description;
    private String imgUrl;
    private boolean status;
    private String email;
    private String statusTypeID;

    public OrganizationDTO() {
        this.orgID = "";
        this.orgName = "";
        this.createDate = "";
        this.description = "";
        this.imgUrl = "";
        this.status = false;
        this.email = "";
        this.statusTypeID = "";
    }

    public OrganizationDTO(String orgID, String orgName, String createDate, String description, String imgUrl, boolean status) {
        this.orgID = orgID;
        this.orgName = orgName;
        this.createDate = createDate;
        this.description = description;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public OrganizationDTO(String orgID, String orgName, String createDate, String description, String imgUrl, boolean status, String email, String statusTypeID) {
        this.orgID = orgID;
        this.orgName = orgName;
        this.createDate = createDate;
        this.description = description;
        this.imgUrl = imgUrl;
        this.status = status;
        this.email = email;
        this.statusTypeID = statusTypeID;
    }
    
    

    public OrganizationDTO(String orgID, String orgName, String createDate, String description, String imgUrl, boolean status, String email) {
        this.orgID = orgID;
        this.orgName = orgName;
        this.createDate = createDate;
        this.description = description;
        this.imgUrl = imgUrl;
        this.status = status;
        this.email = email;
    }

    public OrganizationDTO(String orgID, String orgName, String description, String imgUrl, boolean status) {
        this.orgID = orgID;
        this.orgName = orgName;
        this.description = description;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public String getStatusTypeID() {
        return statusTypeID;
    }

    public void setStatusTypeID(String statusTypeID) {
        this.statusTypeID = statusTypeID;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getOrgID() {
        return orgID;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "OrganizationDTO{" + "orgID=" + orgID + ", orgName=" + orgName + ", createDate=" + createDate + ", description=" + description + ", imgUrl=" + imgUrl + ", status=" + status + '}';
    }

}
