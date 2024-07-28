package Database_Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SocializeDB {
         private final Connection connection;

    public SocializeDB() throws SQLException, ClassNotFoundException {
        connection = this.getConnection();
    }

    public final Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/socializedb?useSSL=false&&allowPublicKeyRetrieval=true",
                "perc",
                "percyy"
        );
    }
}
