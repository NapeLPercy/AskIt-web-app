package Services;

import Data_Access_Objects.ImageDAO;
import java.sql.SQLException;

public class ImageService {

    private final ImageDAO imageDao;

    public ImageService() throws SQLException, ClassNotFoundException {
        imageDao = new ImageDAO();
    }

    public boolean addImage(byte[] image, int postId) throws SQLException, ClassNotFoundException {
        return imageDao.addImage(image, postId);
    }//end
    
}
