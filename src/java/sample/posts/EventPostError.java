package sample.posts;

public class EventPostError {

    private String id;
    private String title;
    private String content;
    private String createDate;
    private String imgUrl;
    private String numberOfView;
    private String summary;
    private String status;
    private String takePlaceDate;
    private String location;
    private String eventType;
    private String speaker;

    public EventPostError() {
        this.id = "";
        this.title = "";
        this.content = "";
        this.createDate = "";
        this.imgUrl = "";
        this.numberOfView = "";
        this.summary = "";
        this.status = "";
        this.takePlaceDate = "";
        this.location = "";
        this.eventType = "";
        this.speaker = "";
    }

    public EventPostError(String id, String title, String content, String createDate, String imgUrl, String numberOfView, String summary, String status, String takePlaceDate, String location, String eventType, String speaker) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.imgUrl = imgUrl;
        this.numberOfView = numberOfView;
        this.summary = summary;
        this.status = status;
        this.takePlaceDate = takePlaceDate;
        this.location = location;
        this.eventType = eventType;
        this.speaker = speaker;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNumberOfView() {
        return numberOfView;
    }

    public void setNumberOfView(String numberOfView) {
        this.numberOfView = numberOfView;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTakePlaceDate() {
        return takePlaceDate;
    }

    public void setTakePlaceDate(String takePlaceDate) {
        this.takePlaceDate = takePlaceDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    
    
}
