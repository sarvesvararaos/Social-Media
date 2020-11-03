package com.project.social_media.repository;

import com.project.social_media.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    @Query("Select COUNT(U)>0 from UserProfile U where U.username = ?1")
    boolean isUsernameExists(String username);

    @Query("Select COUNT(U)>0 from UserProfile U where ?1 IN elements(U.followers) and U.username=?1")
    boolean isFollowerExists(String followers);

    UserProfile findByUsername(String username);
}
