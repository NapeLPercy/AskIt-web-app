package Data_Access_Objects;

import Database_Manager.PrimaryKey;
import Database_Manager.SocializeDB;
import com.mysql.cj.jdbc.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageDAO {

    private final SocializeDB connection;

    public ImageDAO() throws SQLException, ClassNotFoundException {
        connection = new SocializeDB();
    }

    public boolean addImage(byte[] image, int postId) throws SQLException, ClassNotFoundException {
        //primary key
        PrimaryKey pk = new PrimaryKey(connection);

        int imageId = pk.randomPrimaryKey();
        while (pk.isIdPresent(imageId, "image_id", "image")) {
            imageId = pk.randomPrimaryKey();
        }
        
        String sql = "INSERT INTO socializedb.image "
                + "VALUES(?,?,?)";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, imageId);
            ps.setBytes(2, image);
            ps.setInt(3,postId);//fk
            
            ps.executeUpdate();
            ps.close();
        }

        return true;
    }//end
    
    
}
