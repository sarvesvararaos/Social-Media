import { PostsModel } from './PostsModel';
export class UserProfileModel {
  username:string;
  followers:Array<string>;
  following:Array<string>;
  posts:PostsModel;

}
