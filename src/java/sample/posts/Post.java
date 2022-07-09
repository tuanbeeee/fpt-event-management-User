/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.posts;

/**
 *
 * @author light
 */
public abstract class Post {

    private String id;
    private String orgID;
    private String title;
    private String content;
    private String createDate;
    private String imgUrl;
    private int numberOfView;
    private String summary;
    private boolean status;

    public Post() {
    }

    public Post(String id, String orgID, String title, String content, String createDate, String imgUrl, int numberOfView, String summary, boolean status) {
        this.id = id;
        this.orgID = orgID;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.imgUrl = imgUrl;
        this.numberOfView = numberOfView;
        this.summary = summary;
        this.status = status;
    }

    public Post(String id, String orgID, String title, String content, String createDate, String imgUrl, int numberOfView, String summary) {
        this.id = id;
        this.orgID = orgID;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.imgUrl = imgUrl;
        this.numberOfView = numberOfView;
        this.summary = summary;
    }

    public Post(String id, int numberOfView) {
        this.numberOfView = numberOfView;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
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

    public int getNumberOfView() {
        return numberOfView;
    }

    public void setNumberOfView(int numberOfView) {
        this.numberOfView = numberOfView;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", orgID=" + orgID + ", title=" + title + ", content=" + content + ", createDate=" + createDate + ", imgUrl=" + imgUrl + ", numberOfView=" + numberOfView + ", summary=" + summary + ", status=" + status + '}';
    }

}
