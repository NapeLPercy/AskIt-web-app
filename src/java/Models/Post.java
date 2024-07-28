package Models;

import java.util.ArrayList;
import java.util.Date;

public class Post {

    private String message;
    private String status;
    private int likeCount;
    private int dislikeCount;
    private Date creationDate;
    private Video postVideo;//ONE TO ONE
    private Image postImage;//ONE TO ONE
    private ArrayList<Comment> comments = new ArrayList<>();//ONETO MANY
    private int postId;
    private int userId;

    public Post(String message, String status, int likeCount, int dislikeCount) {
        this.message = message;
        this.status = status;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Video getPostVideo() {
        return postVideo;
    }

    public void setPostVideo(Video postVideo) {
        this.postVideo = postVideo;
    }

    public Image getPostImage() {
        return postImage;
    }

    public void setPostImage(Image postImage) {
        this.postImage = postImage;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
