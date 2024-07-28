package Data_Access_Objects;

import Database_Manager.PrimaryKey;
import Database_Manager.SocializeDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VideoDAO {
    
    private final SocializeDB connection;
    
    public VideoDAO() throws SQLException, ClassNotFoundException{
    connection = new SocializeDB();
    }
    
        public boolean addVideo(byte[] video, int postId) throws SQLException, ClassNotFoundException {
        //primary key
        PrimaryKey pk = new PrimaryKey(connection);

        int videoId = pk.randomPrimaryKey();
        while (pk.isIdPresent(videoId, "video_id", "video")) {
            videoId = pk.randomPrimaryKey();
        }
        
        String sql = "INSERT INTO socializedb.video "
                + "VALUES(?,?,?)";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, videoId);
            ps.setBytes(2, video);
            ps.setInt(3,postId);//fk
            
            ps.executeUpdate();
            ps.close();
        }

        return true;
    }//end
    
}
