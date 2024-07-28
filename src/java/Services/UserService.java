package Services;

import Data_Access_Objects.UserDAO;
import Models.User;
import java.sql.SQLException;

public class UserService {

    private final UserDAO userDao;

    public UserService() throws SQLException, ClassNotFoundException {
        userDao = new UserDAO();
    }

    public boolean addUser(User user) throws SQLException, ClassNotFoundException {
        return userDao.addUser(user);
    }//end

    public boolean validateUser(String mobile, String password) throws SQLException, ClassNotFoundException {
        return userDao.validateUser(mobile, password);
    }//end
    
    public int getUserId(String mobile, String password)throws SQLException, ClassNotFoundException{
     return userDao.getUserId(mobile, password);
    }//end

}
