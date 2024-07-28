//When user clicks on a Post's comment logo 
document.querySelectorAll(".comment")
        .forEach(element => {
            element.addEventListener("click", (event) => {

                // Use 'className' to get the class list 
                const postId = event.target.parentElement.parentElement.parentElement.getAttribute("id"); //.closest("ancestor class")
                showPostComments(postId);
            });
        });

//Displays each post's comments
function showPostComments(postId) {
    const xhr = new XMLHttpRequest();

    xhr.open("GET", "/AskIt/CommentController?id=" + postId, true);

    xhr.setRequestHeader("requestType", "renderComments");

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhr.responseText).comments[0];//comments list as js object

            const commentsContainer = document.getElementById("comments-container");
            //render all the comments under the post
            Object.keys(data)
                    .forEach(key => {
                       console.log(data[key]);
        });
    }
    };
    xhr.send();
}


//When user clicks on a actual comment
function computeCommentReaction(reaction) {

    const xhr = new XMLHttpRequest();

    xhr.open("GET", "/AskIt/CommentController", true);
    xhr.setRequestHeader("requestType", reaction);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhr.responseText);
            if (data.requestType === "Like") {
                //increase the number of like on the ui
                //chage the like logo on the ui
                //change the number of dislikes on the ui
                //change the dislikes logo
            } else {
                //change the number of dislikes on the ui
                //change the dislikes logo
                //increase the number of like on the ui
                //chage the like logo on the ui
            }
        }
    };

}//end
//mousedown, mousein, mouseout, mouseup
//When the user holds on a specific comment
document.querySelectorAll("comments")
        .forEach(comment => {
            comment.addEventListener("click", () => {

            });
        });


//When user clicks on a actual comment
function computePostReaction(reaction, postId) {
    const xhr = new XMLHttpRequest();

    xhr.open("POST", "/AskIt/PostController", true);
    xhr.setRequestHeader("requestType", reaction);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const data = JSON.parse(xhr.responseText);

            if (data.requestType === "like") {
                event.target.nextElementSibling.innerHTML = data.likes;
                //increase the number of like on the ui
                //chage the like logo on the ui, done
                //change the number of dislikes on the ui
                //change the dislikes logo, done
            } else {
                //change the number of dislikes on the ui
                //change the dislikes logo, done
                //increase the number of like on the ui
                //chage the like logo on the ui, done
            }
        }
    };
    var formData = new FormData();
    formData.append("postId", postId);
    xhr.send(formData);
}//end

//Handles the liking of a post
document.querySelectorAll(".reaction")
        .forEach(reactionLogo => {
            reactionLogo.
                    addEventListener("click", (event) => {

                        var postId = event.target.parentElement.parentElement.parentElement.id;
                        var reactionType = event.target.id;


                        computePostReaction(reactionType, postId);//Update the like and dislikes count in the database

                        //change the logos after any reaction
                        if (reactionType === "like") {
                            event.target.src = "Icons/likedark.png";
                            event.target.parentElement.nextElementSibling.firstElementChild.src = "Icons/dislikelight.png";

                        } else {
                            event.target.src = "Icons/dislikedark.png";
                            event.target.parentElement.previousElementSibling.firstElementChild.src = "Icons/likelight.png";
                        }

                    });
        });


/* sending data as JSON data
 Read the JSON data from the request body
 StringBuilder jsonString = new StringBuilder();
 String line;
 try (BufferedReader reader = request.getReader()) {
 while ((line = reader.readLine()) != null) {
 jsonString.append(line);
 }
 }
 
 // Parse the JSON data
 JSONObject jsonData = new JSONObject(jsonString.toString());
 
 // Extract the postId from the JSON object
 int postId = jsonData.getInt("postId");
 */
