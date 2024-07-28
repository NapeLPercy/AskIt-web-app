package Services;
;
import Data_Access_Objects.VideoDAO;
import java.sql.SQLException;
import java.sql.SQLException;

public class VideoService {
    
    private final VideoDAO videoDao;

    public VideoService() throws SQLException, ClassNotFoundException {
        videoDao = new VideoDAO();
    }

    public boolean addVideo(byte[] image, int postId) throws SQLException, ClassNotFoundException {
        return videoDao.addVideo(image, postId);
    }//end
}
