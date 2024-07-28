package Data_Access_Objects;

import Database_Manager.PrimaryKey;
import Database_Manager.SocializeDB;
import Models.Comment;
import java.sql.Date;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAO {

    private final SocializeDB connection;

    public CommentDAO() throws SQLException, ClassNotFoundException {
        connection = new SocializeDB();
    }

    public boolean addComment(Comment comment, int postId) throws SQLException, ClassNotFoundException {
        //primary key
        PrimaryKey pk = new PrimaryKey(connection);

        int commentId = pk.randomPrimaryKey();
        while (pk.isIdPresent(postId, "comment_id", "comment")) {
            commentId = pk.randomPrimaryKey();
        }

        //query
        String sql = "INSERT INTO socializedb.post "
                + "VALUES(?,?,?,?,?,CURRENT_TIMESTAMP,?)";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, commentId);
            ps.setString(2, comment.getMessage());
            ps.setInt(3, comment.getLikeCount());
            ps.setInt(5, comment.getDislikeCount());
            ps.setInt(6, postId);//fk

            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

    public boolean editCommentMessage(Comment comment) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE socializedb.comment "
                + "SET message =  ? WHERE comment_id = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setString(1, comment.getMessage());
            ps.setInt(2, comment.getCommentId());

            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

    public boolean deleteComment(Comment comment) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM socializedb.comment "
                + "WHERE comment_id = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, comment.getCommentId());

            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

    public ArrayList<Comment> getComments(int postId) throws SQLException, ClassNotFoundException {

        String sql = "SELECT message, like_count, dislike_count, creation_date, owner "
                +"FROM socializedb.comment "
                + "WHERE post_id = ?";

        ArrayList<Comment> comments = new ArrayList<>();

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, postId);
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    String message = rs.getString("message");
                    int likes = rs.getInt("like_count");
                    int dislikes = rs.getInt("dislike_count");
                    Date date = rs.getDate("creation_date");
                    String owner = rs.getString("owner");
                    Comment comment = new Comment(message, likes, dislikes, owner);
                    comment.setCreationDate(date);

                    comments.add(comment);
                }

                rs.close();
            }
            ps.close();
        }

        return comments;
    }//end

}
