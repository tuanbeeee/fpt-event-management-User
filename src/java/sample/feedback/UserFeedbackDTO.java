/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.feedback;

/**
 *
 * @author Tuan Be
 */
public class UserFeedbackDTO {
    
    public String userID;
    public String eventID;
    public String content;

    public UserFeedbackDTO() {
        this.userID = "";
        this.eventID = "";
        this.content = "";
    }

    public UserFeedbackDTO(String userID, String eventID, String content) {
        this.userID = userID;
        this.eventID = eventID;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
