package Controllers;

import Models.Comment;
import Services.CommentService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class CommentController extends HttpServlet {

    private CommentService commentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            commentService = new CommentService();

            String header = request.getHeader("requestType");
            JSONObject data = new JSONObject();
            
            switch (header) {
                case "renderComments" -> {
                    int postId = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Comment> comments = commentService.getComments(postId);
                    //Gson gson = new Gson();
                    // String jsonResponse = gson.toJson(comments);
                    data.put("comments", comments);
                }
                case "like" -> {
                }
                case "dislike" -> {
                }
                default -> {
                }
            }

            PrintWriter writer = response.getWriter();
            writer.write(data.toString());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
