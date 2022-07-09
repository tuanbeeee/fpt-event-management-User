
package sample.posts;

public class EventType {
    private String eventTypeID;
    private String eventTypeName;

    public EventType() {
    }

    public EventType(String eventTypeID, String eventTypeName) {
        this.eventTypeID = eventTypeID;
        this.eventTypeName = eventTypeName;
    }

    public String getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(String eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    @Override
    public String toString() {
        return "EventType{" + "eventTypeID=" + eventTypeID + ", eventTypeName=" + eventTypeName + '}';
    }
    
}
