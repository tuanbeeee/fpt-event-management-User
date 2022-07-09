/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.users;

/**
 *
 * @author light
 */
public class ManagerDTO extends UserDTO {

    private String orgID;
    private String orgName;

    public ManagerDTO(String orgID, String userID, String fullName, String password, String email, boolean status, String typeID, String roleID, String gender, String phoneNumber, String avatarUrl) {
        super(userID, fullName, password, email, status, typeID, roleID, gender, phoneNumber, avatarUrl);
        this.orgID = orgID;
    }

    public ManagerDTO(String orgID, String id, String name, String password, String email, boolean status, String typeID, String typeName, String roleID, String gender, String phoneNumber, String picture) {
        super(id, name, password, email, status, typeID, typeName, roleID, gender, phoneNumber, picture);
        this.orgID = orgID;
    }

    public ManagerDTO(String orgID, String orgName) {
        this.orgID = orgID;
        this.orgName = orgName;
    }

    public ManagerDTO(String orgID, String orgName, String id, String name, String password, String email, boolean status, String typeID, String typeName, String roleID, String gender, String phoneNumber, String picture) {
        super(id, name, password, email, status, typeID, typeName, roleID, gender, phoneNumber, picture);
        this.orgID = orgID;
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    
    
    
    public ManagerDTO() {
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

}
