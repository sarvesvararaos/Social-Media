package com.project.social_media.controller;

import com.project.social_media.model.Post;
import com.project.social_media.model.Response;
import com.project.social_media.model.UserProfile;
import com.project.social_media.repository.PostRepository;
import com.project.social_media.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@CrossOrigin
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/getAllUsers")
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    @GetMapping("/all-posts/{username}")
    public List<Post> getAllPosts (@PathVariable("username") String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        return userProfile.getPosts();

    }

    @PostMapping("/create-post/{username}")
    public ResponseEntity<?> getPost(@PathVariable("username") String user, @RequestBody Post post) {
        if(!post.getImageUrl().isEmpty()) {
            UserProfile userProfile = userProfileRepository.findByUsername(user);
            post.setUserProfile(userProfile);
            return new ResponseEntity(postRepository.save(post), HttpStatus.CREATED);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("failure","image url required"));
        }

    }
}
