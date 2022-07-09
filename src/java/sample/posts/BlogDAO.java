package sample.posts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sample.posts.Blog;
import sample.posts.EventPost;
import sample.util.DBUtils;

public class BlogDAO {

    private static final String GET_ALL_BLOG = "SELECT blogID, orgID, status, title, createDate, content, imgUrl, numberOfView, summary \n"
            + "FROM tblBlog";

    private static final String GET_A_BLOG_BY_ID = "SELECT blogID, orgID, status, title, createDate, content, imgUrl, numberOfView, summary \n"
            + "FROM tblBlog WHERE blogID = ?";

    private static final String GET_ALL_BLOG_BY_ORG = "SELECT blogID, orgID, status, title, createDate, content, imgUrl, numberOfView, summary\n"
            + "FROM tblBlog WHERE orgID = ?";

    private static final String GET_ALL_BLOG_BY_TITLE = "SELECT blogID, orgID, status, title, createDate, content, imgUrl, numberOfView, summary \n"
            + "FROM tblBlog WHERE dbo.ufn_removeMark(title) LIKE ? OR title LIKE ?";

    public List<Blog> getAllBlog() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Blog> listBlog = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL_BLOG);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("blogID");
                String orgID = rs.getString("orgID");
                String createDate = rs.getString("createDate");
                String content = rs.getString("content");
                String title = rs.getString("title");
                String imgUrl = rs.getString("imgUrl");
                int numberOfView = rs.getInt("numberOfView");
                String summary = rs.getString("summary");
                Boolean status = rs.getBoolean("status");

                Blog blog = new Blog(id, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);
                listBlog.add(blog);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listBlog;
    }

    public Blog getAnBlogByID(String blogID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Blog blog = new Blog();
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(GET_A_BLOG_BY_ID);
            ps.setString(1, blogID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("blogID");
                String orgID = rs.getString("orgID");
                String createDate = rs.getString("createDate");
                String content = rs.getString("content");
                String title = rs.getString("title");
                String imgUrl = rs.getString("imgUrl");
                int numberOfView = rs.getInt("numberOfView");
                String summary = rs.getString("summary");
                Boolean status = rs.getBoolean("status");

                blog = new Blog(id, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return blog;
    }

    public List<Blog> getListBlogByTitle(String search) throws SQLException {
        List<Blog> listBlog = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_ALL_BLOG_BY_TITLE);
                ps.setString(1, "%" + search + "%");
                ps.setString(2, "%" + search + "%");

                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("blogID");
                    String orgID = rs.getString("orgID");
                    String createDate = rs.getString("createDate");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    String imgUrl = rs.getString("imgUrl");
                    int numberOfView = rs.getInt("numberOfView");
                    String summary = rs.getString("summary");
                    Boolean status = rs.getBoolean("status");

                    Blog blog = new Blog(id, orgID, title, content, createDate, imgUrl, numberOfView, summary, status);
                    listBlog.add(blog);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listBlog;

    }

}
