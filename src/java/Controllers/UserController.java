package Controllers;

import Models.User;
import Services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController extends HttpServlet {

    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        try {
            //get actual dob
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date utilDate = dateFormat.parse(dob);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            User user = new User(name, surname, gender, sqlDate, mobile, password);
            userService = new UserService();

            userService.addUser(user);//create account

        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("Views/Outputs/AccountCreated.jsp").forward(request, response);
    }
}
