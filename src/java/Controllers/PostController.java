package Controllers;

import Models.Post;
import Services.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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

@MultipartConfig
public class PostController extends HttpServlet {

    private PostService postService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            postService = new PostService();
            String header = request.getHeader("requestType");
            HttpSession session = request.getSession();

            JSONObject data = new JSONObject();

            switch (header) {
                case "message" -> {
                    String message = request.getParameter("message");
                    Post post = new Post(message, "Active", 0, 0);
                    int userId = Integer.parseInt(session.getAttribute("userId").toString());
                    int postId = postService.addPost(post, userId);
                    if (postId != 0) {
                        data.put("added", true);
                    }
                    session.setAttribute("postId", postId);
                }
                case "picture" -> {
                    Part blob = request.getPart("picture");
                    int postId = Integer.parseInt(session.getAttribute("postId").toString());
                    boolean isAdded = postService.addImage(blob, postId);

                    if (isAdded) {
                        data.put("added", isAdded);

                    }
                }
                case "video" -> {
                    Part blob = request.getPart("video");
                    int postId = Integer.parseInt(session.getAttribute("postId").toString());
                    boolean isAdded = postService.addVideo(blob, postId);

                    if (isAdded) {
                        data.put("added", isAdded);
                    }
                }
                case "like" -> {
                    int postId = Integer.parseInt(request.getParameter("postId"));

                    int newLikeCount = postService.addLike(postId);//User likes a post
                    data.put("likes", newLikeCount);
                    data.put("requestType", "like");
                }

                case "dislike" -> {
                    int postId = Integer.parseInt(request.getParameter("postId"));

                    int newDislikeCount = postService.addDislike(postId);//User likes a post
                    data.put("dislikes", newDislikeCount);
                    data.put("requestType", "dislike");
                }
            }

            //send JSON response to the browser
            PrintWriter writer = response.getWriter();
            writer.write(data.toString());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
