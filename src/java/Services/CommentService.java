package Services;

import Data_Access_Objects.CommentDAO;
import Models.Comment;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentService {
    
    private final CommentDAO commentDao;

    public CommentService() throws SQLException, ClassNotFoundException {
        commentDao = new CommentDAO();
    }

    public ArrayList<Comment> getComments(int postId) throws SQLException, ClassNotFoundException{
     return commentDao.getComments(postId);
    }
    
}
