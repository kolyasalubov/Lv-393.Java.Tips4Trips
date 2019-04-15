import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { AccountComponent } from './components/authentication/account/account.component';
import {CreatePostComponent} from "./components/create-post/create-post.component";
import {CreatePostPlaceComponent} from "./components/create-post-place/create-post-place.component";
import {LikeComponent} from "./components/blog/like/like.component";
import {CreatePostRouteComponent} from "./components/create-post-route/create-post-route.component";
import {SingComponent} from "./components/authentication/sing/sing.component";
import {ListOfUsersComponent} from "./components/list-of-users/list-of-users.component";
import {BlogComponent} from "./components/blog/blog/blog.component";
import {AboutePageComponent} from "./components/aboute-page/aboute-page.component";



const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'create-place', component: CreatePostPlaceComponent },
  { path: 'like', component: LikeComponent },
  { path: 'account', component: AccountComponent },
  { path: 'create_place', component: CreatePostPlaceComponent },
  { path: 'create_post', component: CreatePostComponent },
  { path: 'create_route', component: CreatePostRouteComponent },
  { path: 'sing_up', component: SingComponent },
  { path: 'list_of_users', component: ListOfUsersComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'about', component: AboutePageComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
