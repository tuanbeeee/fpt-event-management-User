/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.posts;

public class Blog extends Post{

    public Blog() {
    }

    public Blog(String id, String orgID, String title, String content, String createDate, String imgUrl, int numberOfView, String summary, boolean status) {
        super(id, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);
    }       
}
