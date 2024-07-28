package Data_Access_Objects;

import Database_Manager.PrimaryKey;
import Database_Manager.SocializeDB;
import Models.User;
import java.sql.*;

public class UserDAO {

    private final SocializeDB connection;

    public UserDAO() throws SQLException, ClassNotFoundException {
        connection = new SocializeDB();
    }

    public boolean addUser(User user) throws SQLException, ClassNotFoundException {
        //primary key
        PrimaryKey pk = new PrimaryKey(connection);

        int userId = pk.randomPrimaryKey();
        while (pk.isIdPresent(userId, "user_id", "user")) {
            userId = pk.randomPrimaryKey();
        }

        //query
        String sql = "INSERT INTO socializedb.user "
                + "VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getGender());
            ps.setDate(5, (Date) user.getDob());
            ps.setString(6, user.getMobile());
            ps.setString(7, user.getPassword());
            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

    public boolean validateUser(String mobile, String password) throws SQLException, ClassNotFoundException {

        String sql = "SELECT mobile, password FROM socializedb.user "
                + "WHERE mobile=? AND password=?";

        boolean isValid = false;

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setString(1, mobile);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isValid = true;
                }
                rs.close();
            }
            ps.close();
        }
        return isValid;
    }//end

    public int getUserId(String mobile, String password) throws SQLException, ClassNotFoundException {

        String sql = "SELECT user_id FROM socializedb.user "
                + "WHERE mobile = ? AND password = ? ";

        int userId = 0;
        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setString(1, mobile);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    userId = rs.getInt("user_id");
                }
                rs.close();
            }
            ps.close();
        }
        return userId;
    }//end
    
}
