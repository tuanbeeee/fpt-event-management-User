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
public class UserError {

    private String idError;
    private String nameError;
    private String passwordError;
    private String oldPasswordError;
    private String passwordConfirmError;
    private String emailError;
    private String statusError;
    private String typeIDError;
    private String typeNameError;
    private String roleIDError;
    private String genderError;
    private String phoneNumberError;
    private String pictureError;
    private String orgIDError;
    private String orgNameError;

    public UserError() {
        this.idError = "";
        this.nameError = "";
        this.passwordError = "";
        this.passwordConfirmError = "";
        this.emailError = "";
        this.statusError = "";
        this.typeIDError = "";
        this.typeNameError = "";
        this.roleIDError = "";
        this.genderError = "";
        this.phoneNumberError = "";
        this.pictureError = "";
        this.orgIDError = "";
        this.orgNameError = "";
        this.oldPasswordError = "";

    }

    public UserError(String idError, String nameError, String passwordError, String oldPasswordError, String passwordConfirmError, String emailError, String statusError, String typeIDError, String typeNameError, String roleIDError, String genderError, String phoneNumberError, String pictureError, String orgIDError, String orgNameError) {
        this.idError = idError;
        this.nameError = nameError;
        this.passwordError = passwordError;
        this.oldPasswordError = oldPasswordError;
        this.passwordConfirmError = passwordConfirmError;
        this.emailError = emailError;
        this.statusError = statusError;
        this.typeIDError = typeIDError;
        this.typeNameError = typeNameError;
        this.roleIDError = roleIDError;
        this.genderError = genderError;
        this.phoneNumberError = phoneNumberError;
        this.pictureError = pictureError;
        this.orgIDError = orgIDError;
        this.orgNameError = orgNameError;
    }

    

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getOldPasswordError() {
        return oldPasswordError;
    }

    public void setOldPasswordError(String oldPasswordError) {
        this.oldPasswordError = oldPasswordError;
    }

    public String getPasswordConfirmError() {
        return passwordConfirmError;
    }

    public void setPasswordConfirmError(String passwordConfirmError) {
        this.passwordConfirmError = passwordConfirmError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getTypeIDError() {
        return typeIDError;
    }

    public void setTypeIDError(String typeIDError) {
        this.typeIDError = typeIDError;
    }

    public String getTypeNameError() {
        return typeNameError;
    }

    public void setTypeNameError(String typeNameError) {
        this.typeNameError = typeNameError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getGenderError() {
        return genderError;
    }

    public void setGenderError(String genderError) {
        this.genderError = genderError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

    public String getPictureError() {
        return pictureError;
    }

    public void setPictureError(String pictureError) {
        this.pictureError = pictureError;
    }

    public String getOrgIDError() {
        return orgIDError;
    }

    public void setOrgIDError(String orgIDError) {
        this.orgIDError = orgIDError;
    }

    public String getOrgNameError() {
        return orgNameError;
    }

    public void setOrgNameError(String orgNameError) {
        this.orgNameError = orgNameError;
    }

    

}
