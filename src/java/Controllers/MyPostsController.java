package Controllers;
import Models.Post;
import Services.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyPostsController extends HttpServlet {

    private PostService postService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            postService = new PostService();
            int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
            
            ArrayList<Post> posts = postService.getPosts(userId);
            
            request.setAttribute("posts",posts);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MyPostsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getRequestDispatcher("/Views/Outputs/MyPosts.jsp").forward(request, response);
    }
}
