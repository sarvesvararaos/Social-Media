import { PostsModel } from './model/PostsModel';
import { PostService } from './service/post.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'social-media';

  getAllPosts:PostsModel[] = [];
  constructor(private postService:PostService) { }

  ngOnInit(): void {
  }
}
