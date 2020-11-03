
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PostsComponent } from './components/posts/posts.component';


const routes: Routes = [

  {path:'users', component:UserProfileComponent},
  {path:'create-post/:username',component:PostsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
