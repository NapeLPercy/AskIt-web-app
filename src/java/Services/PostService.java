package Services;

import Data_Access_Objects.PostDAO;
import Models.Post;
import Utils.PartConverter;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostService {

    private final PostDAO postDao;
    private final ImageService imageService;
    private final VideoService videoService;
    private final PartConverter pc;

    public PostService() throws SQLException, ClassNotFoundException {
        postDao = new PostDAO();
        imageService = new ImageService();
        videoService = new VideoService();
        pc = new PartConverter();
    }

    public int addPost(Post post, int userId) throws SQLException, ClassNotFoundException {
        return postDao.addPost(post, userId);
    }

    public boolean addImage(Part part, int postId) throws SQLException, ClassNotFoundException {

        byte[] image = null;
        try {
            image = pc.getByte(part);
        } catch (IOException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageService.addImage(image, postId);
    }

    public boolean addVideo(Part part, int postId) throws SQLException, ClassNotFoundException {

        byte[] video = null;
        try {
            video = pc.getByte(part);
        } catch (IOException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return videoService.addVideo(video, postId);
    }

    public ArrayList<Post> getPosts(int userId) throws SQLException, ClassNotFoundException {
        return postDao.getPosts(userId);
    }

    public int addLike(int postId) throws SQLException, ClassNotFoundException {
        return postDao.addLike(postId);
    }

    public int addDislike(int postId) throws SQLException, ClassNotFoundException {
        return postDao.addDislike(postId);
    }//end

}
