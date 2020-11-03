import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  private url: string;
  constructor(private http:HttpClient) {
    this.url = 'http://localhost:8080/api';
  }

  public getAllUsers() {
    return this.http.get(`${this.url}/getAllUsers`)
  }
  public createUserProfile(username:Object) {
    return this.http.post<any>(`${this.url}/create-user/`,username)
  }

  public getUserProfiles(username:string) {
    return this.http.get<any>(`${this.url}/get-user/${username}`)
  }

  public followUser(usernameA:string,usernameB:string) {
    return this.http.post<any>(`${this.url}/follow/${usernameA}/${usernameB}`,null)
  }


}
