/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.blog;

import sample.posts.Post;

/**
 *
 * @author Tuan Be
 */
public class BlogDTO extends Post {

    private String orgName;

    public BlogDTO(String orgName, String id, String orgID, String title, String content, String createDate, String imgUrl, int numberOfView, String summary, boolean status) {
        super(id, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);
        this.orgName = orgName;
    }

    public BlogDTO(String id, int numberOfView) {
        super(id, numberOfView);
    }
    

    public BlogDTO() {
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "BlogDTO{" + "orgName=" + orgName + '}';
    }

}
