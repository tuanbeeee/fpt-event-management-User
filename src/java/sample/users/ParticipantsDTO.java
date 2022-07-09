/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.users;

/**
 *
 * @author Tuan Be
 */
public class ParticipantsDTO {

    private String userID;
    private String eventID;
    private boolean status;

    public ParticipantsDTO() {
        this.userID = "";
        this.eventID = "";
        this.status = false;
    }

    public ParticipantsDTO(String userID, String eventID, boolean status) {
        this.userID = userID;
        this.eventID = eventID;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ParticipantsDTO{" + "userID=" + userID + ", eventID=" + eventID + ", status=" + status + '}';
    }

}
