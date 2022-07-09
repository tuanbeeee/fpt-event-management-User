/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.eventtype;

/**
 *
 * @author Tuan Be
 */
public class EventTypeDTO {

    public int eventTypeID;
    public String eventTypeName;
    public boolean status;

    public EventTypeDTO() {
        this.eventTypeName = "";
        this.status = false;
        this.eventTypeID = 0;
    }

    public EventTypeDTO(int eventTypeID, String eventTypeName, boolean status) {
        this.eventTypeID = eventTypeID;
        this.eventTypeName = eventTypeName;
        this.status = status;
    }

    public int getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(int eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EventTypeDTO{" + "eventTypeID=" + eventTypeID + ", eventTypeName=" + eventTypeName + ", status=" + status + '}';
    }

}
