package Data_Access_Objects;

import Database_Manager.PrimaryKey;
import Database_Manager.SocializeDB;
import Models.Image;
import Models.Post;
import Models.Video;
import java.sql.Date;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostDAO {

    private final SocializeDB connection;

    public PostDAO() throws SQLException, ClassNotFoundException {
        connection = new SocializeDB();
    }

    public int addPost(Post post, int userId) throws SQLException, ClassNotFoundException {
        //primary key
        PrimaryKey pk = new PrimaryKey(connection);

        int postId = pk.randomPrimaryKey();
        while (pk.isIdPresent(postId, "post_id", "post")) {
            postId = pk.randomPrimaryKey();
        }

        int added = 0;
        //query
        String sql = "INSERT INTO socializedb.post "
                + "VALUES(?,?,?,?,?,CURRENT_TIMESTAMP,?)";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, postId);
            ps.setString(2, post.getMessage());
            ps.setInt(3, post.getLikeCount());
            ps.setInt(4, post.getDislikeCount());
            ps.setString(5, post.getStatus());
            ps.setInt(6, userId);

            ps.executeUpdate();
            ps.close();
            added = postId;
        }
        return postId;
    }//end

    public boolean editPostMessage(Post post) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE socializedb.post "
                + "SET message =  ? WHERE post_id = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setString(1, post.getMessage());
            ps.setInt(2, post.getPostId());

            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

    public ArrayList<Post> getPosts(int userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT p.post_id, p.message, p.like_count, p.dislike_count, p.creation_date, "
                + "i.image, v.video "
                + "FROM socializedb.post p "
                + "LEFT JOIN socializedb.image i ON p.post_id = i.post_id "
                + "LEFT JOIN socializedb.video v ON p.post_id = v.post_id "
                + "WHERE p.user_id = ?";

        ArrayList<Post> posts = new ArrayList<>();

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int postId = rs.getInt("post_id");
                    String message = rs.getString("message");
                    int likes = rs.getInt("like_count");
                    int dislikes = rs.getInt("dislike_count");
                    Date date = rs.getDate("creation_date");

                    byte[] img = rs.getBytes("image");
                    Image image = img != null ? new Image(img) : null; // Handle null image

                    byte[] vid = rs.getBytes("video");
                    Video video = vid != null ? new Video(vid) : null; // Handle null video

                    Post post = new Post(message, "", likes, dislikes);
                    post.setCreationDate(date);
                    post.setPostImage(image);
                    post.setPostVideo(video);
                    post.setPostId(postId);

                    posts.add(post);
                }
            }
        }
        return posts;
    }//end

    public boolean deletePost(Post post) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM socializedb.comment "
                + "WHERE comment_id = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, post.getPostId());

            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

    public int addLike(int postId) throws SQLException, ClassNotFoundException {

        final Connection connect = connection.getConnection();

        //Get like count for a post
        String likeCountSql = "SELECT like_count FROM socializedb.post "
                + "WHERE post_id = ?";

        int likeCount = 0;

        try (PreparedStatement likeCountps = connect.prepareStatement(likeCountSql)) {
            likeCountps.setInt(1, postId);

            try (ResultSet likeCountRs = likeCountps.executeQuery()) {
                if (likeCountRs.next()) {
                    likeCount = likeCountRs.getInt("like_count");
                }
                likeCountRs.close();
            }
            likeCountps.close();
        }

        //Increase like count for a vote. User likes a post    
        likeCount++;

        //Update a post's like count
        String updateLikeCountSql = "UPDATE socializedb.post SET like_count = ?"
                + " WHERE post_id = ?";

        try (PreparedStatement updateLikeCountPs = connect.prepareStatement(updateLikeCountSql)) {

            updateLikeCountPs.setInt(1, likeCount);
            updateLikeCountPs.setInt(2, postId);

            updateLikeCountPs.executeUpdate();
            updateLikeCountPs.close();
        }

        return likeCount; //return the new post like count

    }//end

    public int addDislike(int postId) throws SQLException, ClassNotFoundException {

        final Connection connect = connection.getConnection();

        //Get like count for a post
        String likeCountSql = "SELECT dislike_count FROM socializedb.post "
                + "WHERE post_id = ?";

        int dislikeCount = 0;

        try (PreparedStatement likeCountps = connect.prepareStatement(likeCountSql)) {
            likeCountps.setInt(1, postId);

            try (ResultSet likeCountRs = likeCountps.executeQuery()) {
                if (likeCountRs.next()) {
                    dislikeCount = likeCountRs.getInt("dislike_count");
                }
                likeCountRs.close();
            }
            likeCountps.close();
        }

        //Increase like count for a vote. User likes a post    
        dislikeCount++;

        //Update a post's like count
        String updateLikeCountSql = "UPDATE socializedb.post SET dislike_count = ?"
                + " WHERE post_id = ?";

        try (PreparedStatement updateLikeCountPs = connect.prepareStatement(updateLikeCountSql)) {

            updateLikeCountPs.setInt(1, dislikeCount);
            updateLikeCountPs.setInt(2, postId);

            updateLikeCountPs.executeUpdate();
            updateLikeCountPs.close();
        }

        return dislikeCount; //return the new post like count

}//end

}
