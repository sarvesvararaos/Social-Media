package com.project.social_media.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user_profile"
        , uniqueConstraints = @UniqueConstraint(columnNames="username",name = "uq_username")
)
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int userId;
    private String username;

    @ElementCollection
    private List<String> following = new ArrayList<String>();

    @ElementCollection
    private List<String> followers = new ArrayList<String>();


    @OneToMany(mappedBy = "userProfile" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    public UserProfile() {
        super();
    }


    public UserProfile(String username, List<String> following, List<String> followers) {
        this.username = username;
        this.following = following;
        this.followers = followers;
    }

    @Column(name = "username",unique = true, nullable=false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserProfile(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }
}
