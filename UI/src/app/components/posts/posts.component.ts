import { PostsModel } from './../../model/PostsModel';
import { PostService } from './../../service/post.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { UserProfileService } from 'src/app/service/user-profile.service';
import { UserProfileModel } from 'src/app/model/UserProfileModel';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  username:string = '';
  postForm:FormGroup;
  userPost:PostsModel = new PostsModel();
  userProfileObj:UserProfileModel = new UserProfileModel();
  allUserProfiles: UserProfileModel[];
  constructor(private fb:FormBuilder,
    public route: Router,
    public actRouter: ActivatedRoute,
    private postService:PostService,
    private userService:UserProfileService) { }

  ngOnInit(): void {

    this.actRouter.paramMap.subscribe((params: ParamMap) => {
      if (params.get('username')) {
        this.username = params.get('username');
      }
    });

     this.userService.getUserProfiles(this.username).subscribe(result=>{
      this.userProfileObj = result;
    });

    this.userService.getAllUsers().subscribe(data=>{
      this.allUserProfiles = data as UserProfileModel[];
    });

    this.postForm = this.fb.group({
      caption:[''],
      imageUrl:['',[Validators.required]]
    })

  }

  createPost(){
    this.userPost.caption = this.postForm.get("caption").value;
    this.userPost.imageUrl = this.postForm.get("imageUrl").value;
    this.postService.createPost(this.username,this.userPost).subscribe((result)=>{
      console.log(result)
    },error=>{
      console.log(error);
    })

  }

  addFollower(usernameA:string,usernameB:string) {
    this.userService.followUser(usernameA,usernameB).subscribe(data=>
      {
        console.log(data)
      },error=>{
        alert(error.error.reason)
      });
  }
}
