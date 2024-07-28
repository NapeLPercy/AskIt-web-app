<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
        <!-- Bootstrap CSS CDN -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="/AskIt/CreateAccount.js" defer></script>
        <style>
            .form-container {
                max-width: 500px; /* Max width for form container */
                margin: auto; /* Center the form container */
            }
            button{
                margin-bottom: 50px;
                width: 330px;
                padding-top: 7px;
                padding-bottom: 7px;
                margin: 20px 0px 20px 0px;
                color: white;
            }
            body{
                background-color: #343a40;
                font-family: Arial;
            }

            h2,label{
                color: black;
            }

            #container{
                background-color: lightgray;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5" id="container">
            <div class="form-container">
                <h2 class="text-center">Create Account</h2>
                <form action="/AskIt/UserController" method="POST" id="createAccountForm">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" name="name">
                        <p class="text-danger" id="name_error"></p>
                    </div>
                    
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username">
                        <p class="text-danger" id="username_error"></p>
                    </div>

                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <select class="form-control" id="gender" name="gender">
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="dob">Date of Birth</label>
                        <input type="date" class="form-control" id="dob" name="dob">
                        <p class="text-danger" id="dob_error"></p>
                    </div>

                    <div class="form-group">
                        <label for="number">Phone Number</label>
                        <input type="tel" class="form-control" id="number" name="mobile">
                        <p class="text-danger" id="number_error"></p>
                    </div>



                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                        <p class="text-danger" id="password_error"></p>
                    </div>

                    <div class="form-group">
                        <label for="password">Confirm Password</label>
                        <input type="password" class="form-control" id="confirm_password" name="password">
                        <p class="text-danger" id="confirm_password_error"></p>
                    </div>


                    <button type="submit" id="create_acccount_btn" class="btn btn-primary">Create Account</button>
                </form>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>

