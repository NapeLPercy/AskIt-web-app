<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Post" %>
<%@ page import="Models.Comment" %>
<%@ page import="Models.Video" %>
<%@ page import="Models.Image" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Posts List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="/AskIt/MyPosts.js" defer></script>
        <style>
            .post-container {
                max-width: 600px;
                margin: 20px auto;
                padding: 15px;
                border: 1px solid #ddd;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                background-color: lightgray;
            }
            .media-container img, .media-container video {
                width: 100%;
                border-radius: 10px;
                margin-top: 10px;
            }
            .reactions-container {
                display: flex;
                align-items: center;
                margin-top: 10px;
                justify-content: space-between;
                flex-wrap: nowrap;
            }
            .reaction-item {
                display: flex;
                align-items: center;
                flex: 1; /* Ensures items take up equal space */
                justify-content: center; /* Center items within their container */
            }
            .reaction-item img {
                margin-right: 5px;
            }
            .reaction-item p {
                margin: 0;
            }
            @media (max-width: 600px) {
                .reactions-container {
                    justify-content: space-between; /* Adjust as needed */
                }
                .reaction-item {
                    margin-right: 10px; /* Adjust as needed for spacing */
                }
            }

            body{
                background-color: #343a40;
                font-family: Arial;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <%
            ArrayList<Post> posts = (ArrayList<Post>)request.getAttribute("posts");
            for (Post post : posts) {
                String message = post.getMessage();
                int likeCount = post.getLikeCount();
                int dislikeCount = post.getDislikeCount();
                Date date = post.getCreationDate();
                Video video = post.getPostVideo();
                Image image = post.getPostImage();
                int postId = post.getPostId();
                
                String imageUrl = null;
                String videoUrl = null;
                if (image != null) {
                    imageUrl = Base64.getEncoder().encodeToString(image.getImage());
                }
                if (video != null) {
                    videoUrl = Base64.getEncoder().encodeToString(video.getVideo());
                }
            %>
            <div class="post-container" id="<%=postId%>">
                <p><strong><%=message%></strong></p>
                <p><%=date%></p>
                <%
                if (image != null || video != null) {
                %>
                <div class="media-container">
                    <%
                    if (image != null) {
                    %>
                    <img src="data:image/png;base64,<%=imageUrl%>" alt="Post Image"/>
                    <%
                    }
                    if (video != null) {
                    %>
                    <video controls>
                        <source src="data:video/mp4;base64,<%=videoUrl%>" type="video/mp4">
                    </video>
                    <%
                    }
                    %>
                </div>
                <%
                }
                %>
                <div class="reactions-container">
                    <div class="reaction-item">
                        <img src="Icons/likelight.png" id="like" class="reaction" width="20" height="20" alt="like"/>
                        <p><%=likeCount%></p>
                    </div>
                    <div class="reaction-item">
                        <img src="Icons/dislikelight.png"  id="dislike" class="reaction"  width="20" height="20" alt="dislike"/>
                        <p><%=dislikeCount%></p>
                    </div>
                    <div class="reaction-item">
                        <img src="Icons/comment.png" class="comment" width="20" height="20" alt="comment"/>
                        <p>0</p>
                    </div>
                </div>
                <div id="comments-container"></div>
            </div>
            <%
            }
            %>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>

