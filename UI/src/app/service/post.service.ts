import { PostsModel } from './../model/PostsModel';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PostService {
  private url: string;
  constructor(private http:HttpClient) {
    this.url = 'http://localhost:8080/api';
  }

  public getAllPosts(username:string) {
    return this.http.get<any>(`${this.url}/all-posts/${username}`)
  }

  public createPost(username:string,post:PostsModel):Observable<PostsModel> {
    debugger
    return this.http.post<PostsModel>(`${this.url}/create-post/${username}`,post)
  }
}
