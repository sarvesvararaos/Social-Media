import { UserProfileModel } from './../../model/UserProfileModel';
import { UserProfileService } from './../../service/user-profile.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  userForm:FormGroup;
  isUsernameExists:boolean = false;

  userProfiles:UserProfileModel[] = [];
  constructor( public formBuilder: FormBuilder,
    public route: Router,
    private userService:UserProfileService) { }

    userProfileObj:UserProfileModel = new UserProfileModel();

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      username: ['', [Validators.required]],
    });

    this.userService.getAllUsers().subscribe(data=>{
      this.userProfiles = data as UserProfileModel[];
    });
  }

  username:string;
  saveData(){
    let userProfile = {}
    userProfile["username"]= this.userForm.get('username').value;
    this.userService.createUserProfile(userProfile).subscribe(result=>{
      this.userForm.controls["username"].reset();
      this.username = result.username;
      this.isUsernameExists = true;
      console.log(result);
    },(error)=>{
      alert(error.error.reason)
    })
  }

  getUserDetails(username:string){
    this.route.navigate([`create-post/${username}`]);

  }
}
