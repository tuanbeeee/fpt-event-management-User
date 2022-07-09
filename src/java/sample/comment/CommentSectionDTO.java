/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.comment;

/**
 *
 * @author Tuan Be
 */
public class CommentSectionDTO {
    public int commentID;
    public int replyID;
    public boolean status;
    public String time;
    public String content;
    public String userID;
    public String eventID;
    public String fullName;
    public String img;

    public CommentSectionDTO() {
        this.commentID = 0;
        this.replyID = 0;
        this.status = false;
        this.time = "";
        this.content = "";
        this.userID = "";
        this.eventID = "";
        this.fullName = "";
        this.img = "";
    }

    public CommentSectionDTO(int commentID, int replyID, boolean status, String time, String content, String userID, String eventID, String fullName, String img) {
        this.commentID = commentID;
        this.replyID = replyID;
        this.status = status;
        this.time = time;
        this.content = content;
        this.userID = userID;
        this.eventID = eventID;
        this.fullName = fullName;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

            
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getReplyID() {
        return replyID;
    }

    public void setReplyID(int replyID) {
        this.replyID = replyID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
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

    @Override
    public String toString() {
        return "CommentSectionDTO{" + "commentID=" + commentID + ", replyID=" + replyID + ", status=" + status + ", time=" + time + ", content=" + content + ", userID=" + userID + ", eventID=" + eventID + ", fullName=" + fullName + '}';
    }

        
    
}
