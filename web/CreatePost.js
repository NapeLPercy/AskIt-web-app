
document.addEventListener('DOMContentLoaded', function () {
    var photoCard = document.getElementById('photoCard');
    var videoCard = document.getElementById('videoCard');

//Add photo modal
    photoCard.addEventListener('click', function () {
        $('#photoModal').modal('show');
    });

    var saveChangesPhoto = document.getElementById("saveChangesPhoto");
    saveChangesPhoto.addEventListener("click", () => {

        document.getElementById("pictureSelected").textContent = "Picture Selected!!";//informs user that they selected a picture
        document.getElementById("pictureSelected").style.color = "black";
        $('#photoModal').modal('hide');
    });

//when user selects an image
    var blobPicture = null;
    document.getElementById('picture').addEventListener('change', function (event) {

        var file = event.target.files[0];

        if (file) {
            var reader = new FileReader();
            reader.onload = function (e) {
                // document.getElementById('profilePicture').src = e.target.result;
            };
            reader.readAsDataURL(file);
            blobPicture = file;
        }
    });


    //Add video modal
    videoCard.addEventListener('click', function () {
        $('#videoModal').modal('show');
    });

    var saveChangesVideo = document.getElementById("saveChangesVideo");
    saveChangesVideo.addEventListener("click", () => {

        document.getElementById("videoSelected").textContent = "Video Selected!!";//informs user that they selected a picture
        document.getElementById("videoSelected").style.color = "black";
        $('#videoModal').modal('hide');
    });

    //when user selects a video
    var blobVideo = null;
    document.getElementById('video').addEventListener('change', function (event) {

        var file = event.target.files[0];

        if (file) {
            var reader = new FileReader();
            reader.onload = function (e) {
                // document.getElementById('profilePicture').src = e.target.result;
            };
            reader.readAsDataURL(file);
            blobVideo = file;
        }
    });

    //creating a post when the POST button is clicked
    document.getElementById("createPostBtn")
            .addEventListener("click", function () {

                const message = document.getElementById("message").value.trim();
                if (message === "") {
                    document.getElementById("message_error").textContent = "Message is  required";
                } else {
                    document.getElementById("message_error").textContent = "";
                    //create the post
                    // console.log("Adding post lol");
                    
                    addPostMessage(message);//add message
                    
                    if (blobPicture !== null) {//add the image if the user selected an image
                        addPostPicture(blobPicture);
                    }

                    if (blobVideo !== null) {//add the image if the user selected a video
                        addPostVideo(blobVideo);
                    }
                }
            });



//removes the previous message from the textarea
    document.getElementById("postAnother")
            .addEventListener("click", () => {
                document.getElementById("message").value = "";
            });

    //submit the form when the user wants to see thier posts
    document.getElementById("viewMyPosts")
            .addEventListener("click", () => {
                document.querySelector("#myPostsForm").submit();
            });
});

//Add message
function addPostMessage(message) {

    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/AskIt/PostController?message=" + message, true);
    xhr.setRequestHeader("requestType", "message");

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhr.responseText);

            if (data.added) {//show post created modal
                $('#postCreatedModal').modal('show');

            }
        }
    };

    xhr.send();
}

//Add image
function addPostPicture(file) {

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/AskIt/PostController", true);

    xhr.setRequestHeader("requestType", "picture");

    xhr.readystatechange = function () {
        if (this.status === 200 && this.readyState === 4) {
            const data = JSON.parse(xhr.responseText);
            console.log(data);
        }
    };

    var formData = new FormData();
    formData.append('picture', file);

    xhr.send(formData);
}

//Add video
function addPostVideo(file) {

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/AskIt/PostController", true);

    xhr.setRequestHeader("requestType", "video");

    xhr.readystatechange = function () {
        if (this.status === 200 && this.readyState === 4) {
            const data = JSON.parse(xhr.responseText);
            console.log(data);
        }
    };

    var formData = new FormData();
    formData.append('video', file);

    xhr.send(formData);
}

