<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Post</title>
        <!-- Bootstrap CSS CDN -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="/AskIt/CreatePost.js"></script>
        <style>
            body {
                background-color: #343a40;
                font-family: Arial;
                color: white;
            }
            .form-container {
                max-width: 500px; /* Max width for form container */
                padding-top: 5px;
                margin: auto; /* Center the form container */
            }

            h2{
                color: black;
                margin-bottom: 20px;
                margin-top: 20px;
                text-align: center;
            }
            .icon-card {
                background-color: white; /* Dark background for icon container */
                border-radius: 10px; /* Rounded corners */
                padding: 15px; /* Padding around icons */
                margin-bottom: 10px; /* Bottom margin */
                cursor: pointer; /* Change cursor to pointer on hover */
            }
            .icon-card:hover {
                background-color: gray; /* Darken background on hover */
            }
            .icon-content {
                display: flex; /* Use flexbox for layout */
                align-items: center; /* Center items vertically */
                justify-content: space-between; /* Space evenly between items */
            }
            .icon-content img {
                margin-right: 10px; /* Margin between icon and text */
            }
            .icon-label {
                color:#007BFF; /* Red color for labels */
                font-weight: bold; /* Bold text */
            }
            #create-post-btn {
                padding-top: 7px;
                padding-bottom: 7px;
                margin: 23px 0px 0px 0px;
                color: white;
            }

            <%-- input[type="file"] {
                 color: transparent; /* Hide the text */
             }--%>

            #container{
                max-width: 600px;
                background-color: lightgray;
                height: 70vh;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5" id="container">
            <div class="form-container">
                <h2 class="text-center">Create Post</h2>

                <div class="form-group">
                    <p class="text-danger" id="message_error"></p>
                    <textarea  id="message" class="form-control" name="message" placeholder="Type your message here" rows="4"></textarea>
                </div>

                <div class="icon-card" id="photoCard">
                    <div class="icon-content" id="photoContent">
                        <img src="/AskIt/Icons/photo.png" width="24" height="24" alt="photo"/>
                        <p id="pictureSelected"></p>
                        <span class="icon-label">Photo</span>
                    </div>
                </div>
                <div class="icon-card" id="videoCard">
                    <div class="icon-content" id="videoContent">
                        <img src="/AskIt/Icons/video.png" width="24" height="24" alt="video"/>
                        <p id="videoSelected"></p>
                        <span class="icon-label">Video</span>
                        
                    </div>
                </div>
                <button type="submit" id="createPostBtn" class="btn btn-primary btn-block">POST</button>
            </div>
        </div>

        <!-- Photo Modal -->
        <div class="modal fade" id="photoModal" tabindex="-1" role="dialog" aria-labelledby="photoModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="photoModalLabel">Add a Photo</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input type="file" id="picture" name="photo" accept="image/*" class="form-control"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" id="saveChangesPhoto" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Video Modal -->
        <div class="modal fade" id="videoModal" tabindex="-1" role="dialog" aria-labelledby="videoModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="videoModalLabel">Add a Video</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input type="file" id="video" name="video" accept="video/*" class="form-control"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" id="saveChangesVideo" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Post created modal -->
        <div class="modal fade" id="postCreatedModal" tabindex="-1" role="dialog" aria-labelledby="videoModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" id="btn" class="close" data-dismiss="modal" aria-label="Close" style="color:black">
                            <b>Post created</b>
                        </button>

                    </div>
                    <div class="modal-footer">
                        <button type="button" id="postAnother" class="btn btn-secondary" data-dismiss="modal">Post Another</button>
                        <form action="/AskIt/MyPostsController" id="myPostsForm" method="GET">
                            <button type="button" id="viewMyPosts" class="btn btn-primary">View my posts</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
