package sample.notification;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tuan Be
 */
public class NotificationDTO {
    
    public String userID;
    public String eventID;
    public String evtContent;
    public String notiDate;
    public String notiContent;
    public String orgID;
    public String orgImg;

    public NotificationDTO() {
        this.userID = "";
        this.eventID = "";
        this.evtContent = "";
        this.notiDate = "";
        this.notiContent = "";
        this.orgID = "";
        this.orgImg = "";
    }

    public NotificationDTO(String userID, String eventID, String evtContent, String notiDate, String notiContent, String orgID, String orgImg) {
        this.userID = userID;
        this.eventID = eventID;
        this.evtContent = evtContent;
        this.notiDate = notiDate;
        this.notiContent = notiContent;
        this.orgID = orgID;
        this.orgImg = orgImg;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEvtContent() {
        return evtContent;
    }

    public void setEvtContent(String evtContent) {
        this.evtContent = evtContent;
    }

    public String getNotiDate() {
        return notiDate;
    }

    public void setNotiDate(String notiDate) {
        this.notiDate = notiDate;
    }

    public String getNotiContent() {
        return notiContent;
    }

    public void setNotiContent(String notiContent) {
        this.notiContent = notiContent;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getOrgImg() {
        return orgImg;
    }

    public void setOrgImg(String orgImg) {
        this.orgImg = orgImg;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" + "userID=" + userID + ", eventID=" + eventID + ", evtContent=" + evtContent + ", notiDate=" + notiDate + ", notiContent=" + notiContent + ", orgID=" + orgID + ", orgImg=" + orgImg + '}';
    }
       
}
