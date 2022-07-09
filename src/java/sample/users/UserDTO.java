package sample.users;

public class UserDTO {

    private String id;
    private String name;
    private String password;
    private String email;
    private boolean status;
    private String typeID;
    private String typeName;
    private String roleID;
    private String gender;
    private String phoneNumber;
    private String picture;

    public UserDTO() {
        this.id = "";
        this.name = "";
        this.password = "";
        this.email = "";
        this.status = false;
        this.typeID = "";
        this.roleID = "";
        this.gender = "";
        this.phoneNumber = "";
        this.picture = "";
    }

    public UserDTO(String id, String name, String password, String email, boolean status, String typeID, String roleID, String gender, String phoneNumber, String picture) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.status = status;
        this.typeID = typeID;
        this.roleID = roleID;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
    }

    public UserDTO(String id, String name, String password, String email, boolean status, String typeID, String typeName, String roleID, String gender, String phoneNumber, String picture) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.status = status;
        this.typeID = typeID;
        this.typeName = typeName;
        this.roleID = roleID;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
    }

    public UserDTO(String typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", status=" + status + ", typeID=" + typeID + ", roleID=" + roleID + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", picture=" + picture + '}';
    }

}
