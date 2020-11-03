package com.project.social_media.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @NotBlank(message = "required")
    private String imageUrl;
    private String caption;
    private int upvotes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserProfile userProfile;

    public  Post() {
        super();
    }
    public Post(int postId, @NotBlank(message = "required") String imageUrl, String caption, int upvotes) {
        this.postId = postId;
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.upvotes = upvotes;
    }

    public Post(@NotBlank(message = "required") String imageUrl, String caption) {
        this.imageUrl = imageUrl;
        this.caption = caption;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Column(name="image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Column( columnDefinition = "int4 default 0")
    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
