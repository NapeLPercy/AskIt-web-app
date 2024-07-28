package Database_Manager;

import java.sql.SQLException;
import java.util.Random;
import java.sql.*;

public class PrimaryKey {

    private final SocializeDB connection;

    public PrimaryKey(SocializeDB connection) throws SQLException, ClassNotFoundException {
        this.connection = connection;
    }

    public int randomPrimaryKey() {
        Random random = new Random();
        return random.nextInt(10000);
    }//end

    public boolean isIdPresent(int id, String column, String table) throws SQLException, ClassNotFoundException {

        String sql = "SELECT ? FROM socializedb." + table
                + " WHERE ? = ? ";

        boolean isPresent = false;

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setString(1, column);
            ps.setString(2, column);
            ps.setInt(3, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isPresent = true;
                }
                rs.close();
            }
            ps.close();
        }

        return isPresent;
    }//end

}
