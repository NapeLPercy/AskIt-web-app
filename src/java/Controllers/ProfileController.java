package Controllers;

import Models.Profile;
import Services.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class ProfileController extends HttpServlet {

    private ProfileService profileService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONObject data = new JSONObject();

        HttpSession session = request.getSession();

        try {

            profileService = new ProfileService();

            String header = request.getHeader("requestType");
            int userId = Integer.parseInt(session.getAttribute("userId").toString());
            
            switch (header) {
                case "updateUi" -> {
                    Profile profile = profileService.getProfile(userId);
                    String currentUsername = profile.getUsername();
                    String currentBio = profile.getBio();
                    
                    data.put("Username", currentUsername);
                    data.put("Bio", currentBio);
                }
                case "username" ->                     {
                        //User requests to edit the username
                        
                        String newUsername = request.getParameter("username");
                        boolean isEdited = profileService.editUsername(newUsername, userId);//new username
                        data.put("edited", isEdited);
                    }
                case "bio" ->                     {
                        //User requests to edit the bio

                        String newBio = request.getParameter("bio");
                        boolean isEdited = profileService.editBio(newBio, userId);//new bio
                        data.put("edited", isEdited);
                    }
                case "picture" -> {
                    Part profilePicture = request.getPart("picture");
                    boolean isEdited = profileService.updateProfilePicture(profilePicture, userId);
                    data.put("edited", isEdited);
                }
            }

            //send json response to browser
            PrintWriter writer = response.getWriter();
            writer.write(data.toString());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
