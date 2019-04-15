import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { AccountComponent } from './components/authentication/account/account.component';
import {CreatePostComponent} from "./components/create-post/create-post.component";
import {CreatePostPlaceComponent} from "./components/create-post-place/create-post-place.component";
import {CreatePostRouteComponent} from "./components/create-post-route/create-post-route.component";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'account', component: AccountComponent },
  { path: 'create_place', component: CreatePostComponent },
  { path: 'create_post', component: CreatePostPlaceComponent },
  { path: 'create_route', component: CreatePostRouteComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
