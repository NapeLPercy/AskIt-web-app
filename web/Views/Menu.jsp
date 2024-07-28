<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Menu</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            /* Custom CSS for menu */
            .custom-menu {
                background-color: #343a40; 
                padding: 10px 0;
            }
            .custom-menu .navbar-brand {
                color: #ffffff; 
                font-weight: bold; 
            }
            .custom-menu .navbar-nav .nav-link {
                color: #ffffff; 
                padding: 0.5rem 1rem; 
            }
            .custom-menu .navbar-nav .nav-link:hover {
                background-color: #555d66; 
            }
            span{
                font-family: Arial,
                    font-weight: bold;
                color: red;
                font-size: 23px;
            }

            #logout{
                margin:10px 0px 5px 15px;
                border-radius: 10px;
            }
        </style>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark custom-menu">
            <div class="container">
                <a class="navbar-brand" href="#" style="margin-left: 10px"><span>Social</span>ize</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" 
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"
                        style="margin-right: 10px">
                    <span>Menu</span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/AskIt/Views/Inputs/MyProfile.jsp">My Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/AskIt/Views/Inputs/CreatePost.jsp">Create Post</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/AskIt/MyPostsController">My Posts</a>
                        </li>
                    </ul>
                    <button id="logout">
                        <a class="nav-link" style="color: red" href="#"><b>Logout</b></a>
                    </button>

                </div>
            </div>
        </nav>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>
