package Services;

import Data_Access_Objects.ProfileDAO;
import Models.Profile;
import Utils.PartConverter;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileService {

    private final ProfileDAO profileDao;
    private final PartConverter pc;

    public ProfileService() throws SQLException, ClassNotFoundException {
        profileDao = new ProfileDAO();
        pc = new PartConverter();
    }

    public boolean editUsername(String username, int userId) throws SQLException, ClassNotFoundException {
        return profileDao.editUsername(username, userId);
    }

    public boolean editBio(String newBio, int userId) throws SQLException, ClassNotFoundException {
        return profileDao.editBio(newBio, userId);
    }

    public Profile getProfile(int userId) throws SQLException, ClassNotFoundException {
        return profileDao.getProfile(userId);
    }

    public boolean updateProfilePicture(Part profilePicture, int userId) throws SQLException, ClassNotFoundException {
        byte[] newProfilePicture = null;
        try {
            newProfilePicture = pc.getByte(profilePicture);
        } catch (IOException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profileDao.updateProfilePicture(newProfilePicture, userId);
    }
}
