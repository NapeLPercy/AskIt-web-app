package Models;

import java.util.ArrayList;
import java.util.Date;

public class User {

    private String name;
    private String surname;
    private String gender;
    private Date dob;
    private String mobile;
    private String password;
    private Profile profile;//ONE TO ONE
    ArrayList<Post> posts = new ArrayList<>();//ONE TO MANY
    private int userId;

    public User(String name, String surname, String gender, Date dob, String mobile, String password) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dob = dob;
        this.mobile = mobile;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
