// Function to handle clicking on edit links
document.addEventListener('DOMContentLoaded', function () {

    var editUsernameLink = document.getElementById('editUsernameLink');
    var editProfilePicLink = document.getElementById('editProfilePicLink');
    var editBioLink = document.getElementById('editBioLink');

    editUsernameLink.addEventListener('click', function () {
        $('#editUsernameModal').modal('show');
    });

    editProfilePicLink.addEventListener('click', function () {
        $('#editProfilePicModal').modal('show');
    });

    editBioLink.addEventListener('click', function () {
        $('#editBioModal').modal('show');
    });

    // Function to handle saving username
    document.getElementById('saveUsernameBtn').addEventListener('click', function () {
        var newUsername = document.getElementById('newUsername').value.trim();

        //edit the username in the database
        editUsername(newUsername);

        // Close modal
        $('#editUsernameModal').modal('hide');
    });

 //When user selects an image on a modal
        var newProfilePic = null;
        document.getElementById('picture').addEventListener('change', function (event) {

            var file = event.target.files[0];

            if (file) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    // document.getElementById('profilePicture').src = e.target.result;
                };
                reader.readAsDataURL(file);
                newProfilePic = file;
            }
        });
    // Function to handle saving profile picture
    document.getElementById('saveProfilePicBtn').addEventListener('click', function () {

        // Updates the profile picture in the database
       updateProfilePicture(newProfilePic);


        $('#editProfilePicModal').modal('hide');
    });

    // Function to handle saving bio
    document.getElementById('saveBioBtn').addEventListener('click', function () {
        var newBio = document.getElementById('newBio').value.trim();

        //Edit current bio display
        editBio(newBio);
        // Close modal
        $('#editBioModal').modal('hide');
    });
});


//Edit user's username
function editUsername(username) {

    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/AskIt/ProfileController?username=" + username, true);
    xhr.setRequestHeader("requestType", "username");

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhr.responseText);

            //Update the ui if the username was updated successufully in the database
            if (data.edited) {
                document.getElementById("username").textContent = username;
            }
        }
    };

    xhr.send();
}//end


//Edit user's bio
function editBio(bio) {

    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/AskIt/ProfileController?bio=" +bio, true);
    xhr.setRequestHeader("requestType", "bio");

    xhr.onreadystatechange = function () {
        if (this.status === 200 && this.readyState === 4) {

            const data = JSON.parse(xhr.responseText);
            //Update the ui if the Bio was updated successufully in the database 
            if (data.edited) {
                document.getElementById("bio").textContent = bio;
            }
        }
    };
    xhr.send();
}

//Edit user's bio
function editBio(picture) {

    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/AskIt/ProfileController", true);
    xhr.setRequestHeader("requestType", "picture");

    xhr.onreadystatechange = function () {
        if (this.status === 200 && this.readyState === 4) {

            const data = JSON.parse(xhr.responseText);
            //Update the ui if the Profile was updated successufully in the database 
            if (data.edited) {
                document.getElementById("profilePicture").src = picture;
            }
        }
    };
    
    var formData = new FormData();
    formData.append("picture",picture);
    xhr.send(formData);
}


//Updates profile details to the current details whenever a user access the profile
document.querySelector("body").addEventListener("load", updateProfileUi());
function updateProfileUi() {

    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/AskIt/ProfileController", true);
    xhr.setRequestHeader("requestType", "updateUi");

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {

            const data = JSON.parse(xhr.responseText);
            document.getElementById("username").textContent = data.Username;
            document.getElementById("bio").textContent = data.Bio;
        }
    };

    xhr.send();
}//end