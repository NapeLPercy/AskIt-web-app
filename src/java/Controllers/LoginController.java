package Controllers;

import Services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class LoginController extends HttpServlet {

    private UserService userServivce;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String password = request.getParameter("password");
        String mobile = request.getParameter("mobile");
        
        try {

            userServivce = new UserService();
            boolean isValidUser = userServivce.validateUser(mobile, password);

            if (isValidUser) {
                int userId = userServivce.getUserId(mobile, password);          
                request.getSession(isValidUser).setAttribute("userId",userId);
            }
            
            JSONObject data = new JSONObject();
            data.put("Valid", isValidUser);

            PrintWriter writer = response.getWriter();
            writer.write(data.toString());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
