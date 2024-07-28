package Data_Access_Objects;

import Database_Manager.PrimaryKey;
import Database_Manager.SocializeDB;
import Models.Image;
import Models.Profile;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDAO {

    private final SocializeDB connection;

    public ProfileDAO() throws SQLException, ClassNotFoundException {
        connection = new SocializeDB();
    }

    public boolean addProfile(Profile profile, int userId) throws SQLException, ClassNotFoundException {

        //primary key
        PrimaryKey pk = new PrimaryKey(connection);

        int profileId = pk.randomPrimaryKey();
        while (pk.isIdPresent(profileId, "profile_id", "profile")) {
            profileId = pk.randomPrimaryKey();
        }

        String sql = "INSERT INTO socializedb.profile "
                + "VALUES(?,?,?,?)";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, profileId);
            ps.setString(2, profile.getUsername());
            ps.setString(3, profile.getBio());
            ps.setBytes(4, profile.getPicture());//add picture          
            ps.setInt(5, userId);//fk;

            ps.executeUpdate();
            ps.close();
        }

        return true;
    }//end

    public boolean editBio(String bio, int userId) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE socializedb.profile "
                + "SET bio = ?  WHERE user_id = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setString(1, bio);
            ps.setInt(2, userId);
            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

    public boolean editUsername(String username, int userId) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE socializedb.profile "
                + "SET username = ?  WHERE user_id = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setInt(2, userId);
            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

    public boolean editProfilePicture(Image picture, int userId) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE socializedb.profile "
                + "SET picture = ?  WHERE profile_id = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setBytes(1, picture.getImage());//add picture   
            ps.setInt(2, userId);
            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

    public Profile getProfile(int userId) throws SQLException, ClassNotFoundException {

        String sql = "SELECT username, bio FROM socializedb.profile "
                + "WHERE user_id = ? ";

        Profile profile =  null;
        
        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String username = rs.getString("username");
                    String bio = rs.getString("bio");
                    profile = new Profile();
                    profile.setUsername(username);
                    profile.setBio(bio);
                }
                rs.close();
            }
            ps.close();
        }
        return profile;
    }//end

    public boolean updateProfilePicture(byte[] newProfilePicture, int userId) throws SQLException, ClassNotFoundException {
    
        String sql = "UPDATE socializedb.profile "+
                "SET picturer = ? WHERE user_id = ?";
        
        try(PreparedStatement ps = connection.getConnection().prepareStatement(sql)){
            ps.setBytes(1, newProfilePicture);
            ps.setInt(2, userId);
            
            ps.executeUpdate();
            ps.close();
        }
        return true;
    }
    
    }
