package sample.posts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sample.blog.BlogDTO;
import sample.posts.Blog;
import sample.posts.EventPost;
import sample.util.DBUtils;

public class BlogDAO {

    private static final String GET_ALL_BLOG = "SELECT blogID, tblBlog.orgID AS org_ID, orgName, tblBlog.status AS Blog_status, title, tblBlog.createDate AS create_Date, content, tblBlog.imgUrl AS img_URL, numberOfView, summary FROM tblBlog, tblOrgPage WHERE tblBlog.orgID = tblOrgPage.orgID AND tblBlog.status = '1'";

    private static final String GET_BLOG_DETAIL = "SELECT blogID, tblBlog.orgID AS org_ID, orgName, tblBlog.status AS Blog_status, title, tblBlog.createDate AS create_Date, content, tblBlog.imgUrl AS img_URL, numberOfView, summary FROM tblBlog, tblOrgPage WHERE tblBlog.orgID = tblOrgPage.orgID AND tblBlog.status = '1' AND blogID = ?";

    private static final String COUNT_BLOG_VIEW_NUMBER = "UPDATE tblBlog SET  numberOfView = ? WHERE blogID = ?";

    private static final String GET_NEWEST_BLOG = "SELECT TOP 4 blogID, tblBlog.orgID AS org_ID, orgName, tblBlog.status AS Blog_status, title, tblBlog.createDate AS create_Date, content, tblBlog.imgUrl AS img_URL, numberOfView, summary FROM tblBlog, tblOrgPage WHERE tblBlog.orgID = tblOrgPage.orgID AND tblBlog.status = '1' ORDER BY blogID  DESC";

    private static final String SEARCH_BLOG_BY_USER = "SELECT blogID, tblBlog.orgID AS org_ID, orgName, tblBlog.status AS Blog_status, title, tblBlog.createDate AS create_Date, content, tblBlog.imgUrl AS img_URL, numberOfView, summary FROM tblBlog, tblOrgPage WHERE tblBlog.orgID = tblOrgPage.orgID AND tblBlog.status = '1' AND title LIKE ?";

    private static final String SEARCH_BLOG_WITHOUT_MARK_BY_USER = "SELECT blogID, tblBlog.orgID AS org_ID, orgName, tblBlog.status AS Blog_status, title, tblBlog.createDate AS create_Date, content, tblBlog.imgUrl AS img_URL, numberOfView, summary FROM tblBlog, tblOrgPage WHERE tblBlog.orgID = tblOrgPage.orgID AND tblBlog.status = '1' AND dbo.ufn_removeMark(title) LIKE ?";

    private static final String GET_BLOG_WITH_PAGING = "WITH x AS(SELECT ROW_NUMBER() over (order by tblBlog.createDate desc) AS r, blogID, tblBlog.orgID AS org_ID, orgName, tblBlog.status AS Blog_status, title, tblBlog.createDate AS create_Date, content, tblBlog.imgUrl AS img_URL, numberOfView, summary FROM tblBlog, tblOrgPage WHERE tblBlog.orgID = tblOrgPage.orgID AND tblBlog.status = '1') \n"
            + "SELECT * FROM x WHERE r BETWEEN ?*4-3 AND ?*4";

    public BlogDTO getBlogDetail(String blogID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_BLOG_DETAIL;
        boolean status = true;
        BlogDTO blogDetail = new BlogDTO();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, blogID);
                rs = ptm.executeQuery();

                while (rs.next()) {

                    String orgID = rs.getString("org_ID");
                    String createDate = rs.getString("create_Date");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    int numberOfView = rs.getInt("numberOfView");
                    String summary = rs.getString("summary");
                    String imgURL = rs.getString("img_URL");
                    String orgName = rs.getString("orgName");

                    blogDetail = new BlogDTO(orgName, blogID, orgID, title, content, createDate, imgURL, numberOfView, summary, status);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return blogDetail;
    }

    public void countBlogViewNumber(BlogDTO blog) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ptm = null;
        String sql = COUNT_BLOG_VIEW_NUMBER;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);

                ptm.setInt(1, blog.getNumberOfView());
                ptm.setString(2, blog.getId());
                ptm.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList<BlogDTO> getNewestBlog() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_NEWEST_BLOG;
        boolean status = true;
        ArrayList<BlogDTO> blogList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String blogID = rs.getString("blogID");
                    String orgID = rs.getString("org_ID");
                    String createDate = rs.getString("create_Date");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    int numberOfView = rs.getInt("numberOfView");
                    String summary = rs.getString("summary");
                    String imgURL = rs.getString("img_URL");
                    String orgName = rs.getString("orgName");

                    BlogDTO blogInfo = new BlogDTO(orgName, blogID, orgID, title, content, createDate, imgURL, numberOfView, summary, status);

                    blogList.add(blogInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return blogList;
    }

    public ArrayList<BlogDTO> getAllBlog() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_ALL_BLOG;
        boolean status = true;
        ArrayList<BlogDTO> blogList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String blogID = rs.getString("blogID");
                    String orgID = rs.getString("org_ID");
                    String createDate = rs.getString("create_Date");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    int numberOfView = rs.getInt("numberOfView");
                    String summary = rs.getString("summary");
                    String imgURL = rs.getString("img_URL");
                    String orgName = rs.getString("orgName");

                    BlogDTO blogInfo = new BlogDTO(orgName, blogID, orgID, title, content, createDate, imgURL, numberOfView, summary, status);

                    blogList.add(blogInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return blogList;
    }

    public ArrayList<BlogDTO> getBlogWithPaging(int pageIndex) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = GET_BLOG_WITH_PAGING;
        boolean status = true;
        ArrayList<BlogDTO> blogList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, pageIndex);
                ptm.setInt(2, pageIndex);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String blogID = rs.getString("blogID");
                    String orgID = rs.getString("org_ID");
                    String createDate = rs.getString("create_Date");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    int numberOfView = rs.getInt("numberOfView");
                    String summary = rs.getString("summary");
                    String imgURL = rs.getString("img_URL");
                    String orgName = rs.getString("orgName");

                    BlogDTO blogInfo = new BlogDTO(orgName, blogID, orgID, title, content, createDate, imgURL, numberOfView, summary, status);

                    blogList.add(blogInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return blogList;
    }
    
    public ArrayList<BlogDTO> searchBlog(String search) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = SEARCH_BLOG_BY_USER;
        boolean status = true;
        ArrayList<BlogDTO> blogList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String blogID = rs.getString("blogID");
                    String orgID = rs.getString("org_ID");
                    String createDate = rs.getString("create_Date");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    int numberOfView = rs.getInt("numberOfView");
                    String summary = rs.getString("summary");
                    String imgURL = rs.getString("img_URL");
                    String orgName = rs.getString("orgName");

                    BlogDTO blogInfo = new BlogDTO(orgName, blogID, orgID, title, content, createDate, imgURL, numberOfView, summary, status);

                    blogList.add(blogInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return blogList;
    }

    public ArrayList<BlogDTO> searhBlogWithoutMark(String search) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = SEARCH_BLOG_WITHOUT_MARK_BY_USER;
        boolean status = true;
        ArrayList<BlogDTO> blogList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String blogID = rs.getString("blogID");
                    String orgID = rs.getString("org_ID");
                    String createDate = rs.getString("create_Date");
                    String content = rs.getString("content");
                    String title = rs.getString("title");
                    int numberOfView = rs.getInt("numberOfView");
                    String summary = rs.getString("summary");
                    String imgURL = rs.getString("img_URL");
                    String orgName = rs.getString("orgName");

                    BlogDTO blogInfo = new BlogDTO(orgName, blogID, orgID, title, content, createDate, imgURL, numberOfView, summary, status);

                    blogList.add(blogInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return blogList;
    }
    public static void main(String[] args) throws SQLException {
        int i = 1;
        ArrayList<BlogDTO> dto = new  ArrayList<>();
        BlogDAO dao = new BlogDAO();
        dto = dao.getBlogWithPaging(1);
        System.out.println(dto);
    }
}
