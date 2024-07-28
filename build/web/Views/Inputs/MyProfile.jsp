<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="/AskIt/MyProfile.js" defer></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 

        <style>
            .container {
                max-width: 600px; /* Max width for content */
                margin: auto; /* Center content */
                padding: 20px; /* Padding for better spacing */
            }
            .edit-link {
                cursor: pointer; /* Change cursor to pointer */
                color: blue; /* Blue color for link */
            }
            .edit-link:hover {
                text-decoration: underline; /* Underline on hover */
            }
            textarea {
                width: 100%; /* Full width textarea */
                padding: 10px; /* Padding for better appearance */
                margin-bottom: 10px; /* Bottom margin for spacing */
                display: none; /* Initially hide textarea */
            }

            body{
                background-color: #343a40;
                font-family: Arial;
            }

            h2{
                margin-bottom: 20px;
                text-align: center;
            }
            span{
                color: #007BFF;
                font-weight: bold;
            }

            <%--input[type="file"] {
                color: transparent; /* Hide the text */
            }
            --%>

            #container{
                background-color: lightgray;
                height: auto;
                border-radius: 5px;
                margin-bottom: 50px;
            }

        </style>
    </head>
    <body>
        <div class="container mt-5" id="container">
            <h2>Profile</h2>

            <!-- Username Section -->
            <div class="card profile-info mb-3">
                <div class="card-body d-flex justify-content-between align-items-center" >
                    <div>
                        <p class="card-text" id="username"></p>
                    </div>
                    <a class="edit-link" id="editUsernameLink"><span>Edit</span></a>
                </div>
            </div>

            <!-- gender Section -->
            <div class="card profile-info mb-3">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <p class="card-text">Male , born on <b>1999 february 02</b></p>
                    </div>
                </div>
            </div>

            <!-- Profile Picture Section -->
            <div class="card profile-info mb-3">
                <div class="card-body d-flex justify-content-between align-items-center" >
                    <div class="d-flex align-items-center">
                        <img src="/Socialize/Icons/profile.png" class="rounded-circle mr-3" id="profilePicture" alt="Profile Picture" width="96" height="96" />
                    </div>
                    <a class="edit-link" id="editProfilePicLink"><span>Edit</span></a>
                </div>
            </div>

            <!-- Bio Section -->
            <div class="card profile-info mb-3">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-9">
                            <p class="card-text" id="bio"></p>
                        </div>
                    </div>
                    <a class="edit-link" id="editBioLink"><span>Edit bio</span></a>
                </div>
            </div>

        </div>

        <!-- Edit Username Modal -->
        <div class="modal fade" id="editUsernameModal" tabindex="-1" role="dialog" aria-labelledby="editUsernameModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editUsernameModalLabel">Edit Username</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Username edit form -->
                        <form id="usernameForm">
                            <div class="form-group">
                                <label for="newUsername">New Username:</label>
                                <input type="text" class="form-control" id="newUsername">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="saveUsernameBtn">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Edit Profile Picture Modal -->
        <div class="modal fade" id="editProfilePicModal" tabindex="-1" role="dialog" aria-labelledby="editProfilePicModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editProfilePicModalLabel">Edit Profile Picture</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Profile picture edit form -->
                        <form id="profilePicForm">
                            <div class="form-group">
                                <label for="newProfilePic">New Profile Picture:</label>
                                <input type="file" class="form-control-file" accept="image/*" id="newProfilePic">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="saveProfilePicBtn">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Edit Bio Modal -->
        <div class="modal fade" id="editBioModal" tabindex="-1" role="dialog" aria-labelledby="editBioModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editBioModalLabel">Edit Bio</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Bio edit form -->
                        <form id="bioForm">
                            <div class="form-group">
                                <label for="newBio">New Bio:</label>
                                <textarea class="form-control" id="newBio" rows="4"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="saveBioBtn">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>


