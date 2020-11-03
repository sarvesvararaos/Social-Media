package com.project.social_media.controller;

import com.project.social_media.model.Response;
import com.project.social_media.model.UserProfile;
import com.project.social_media.repository.UserProfileRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;


    @GetMapping("get-user/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable("username") String username) {
        if(!username.isEmpty()) {
            UserProfile userProfile = userProfileRepository.findByUsername(username);
            if(userProfile != null) {
                return new ResponseEntity(userProfile,HttpStatus.OK);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUserProfile(@Valid  @RequestBody UserProfile userProfile) {
        boolean isExists = userProfileRepository.isUsernameExists(userProfile.getUsername());
        if(!userProfile.getUsername().isEmpty() && !isExists){
            UserProfile user = userProfileRepository.save(userProfile);
            JSONObject json=new JSONObject();
            json.put("username",user.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(json);
        }
        else if(isExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response("failure","username already exists"));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("failure","Bad Request"));
        }
    }

    @PostMapping("follow/{usernameA}/{usernameB}")
    public ResponseEntity<?> followUser(@PathVariable("usernameA") String follower, @PathVariable("usernameB") String toFollow) {
        UserProfile userProfile = userProfileRepository.findByUsername(toFollow);

        if (userProfile != null && !userProfileRepository.isFollowerExists(follower)) {
            List<String> followers = userProfile.getFollowers();
                followers.add(follower);
                userProfile.setFollowers(followers);
                userProfileRepository.save(userProfile);
                JSONObject json=new JSONObject();
                json.put("status","success");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("failure","username does not exists or already following"));
        }

    }

}   
