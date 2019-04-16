import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { AccountComponent } from './components/authentication/account/account.component';
import { RouteComponent } from './components/route/route.component';
import {CreatePostComponent} from "./components/create-post/create-post.component";
import {CreatePostPlaceComponent} from "./components/create-post-place/create-post-place.component";
import {LikeComponent} from "./components/blog/like/like.component";
import {CreateRouteComponent} from "./components/create-route/create-route.component";
import {SignupComponent} from "./components/authentication/signup/signup.component";
import {ListOfUsersComponent} from "./components/list-of-users/list-of-users.component";
import {BlogComponent} from "./components/blog/blog/blog.component";
import {AboutePageComponent} from "./components/aboute-page/aboute-page.component";
import {CreateRestaurantComponent} from "./components/create-post-place/create-restaurant/create-restaurant.component";
import {CreateHotelComponent} from "./components/create-post-place/create-hotel/create-hotel.component";
import {BigPostComponent} from "./components/blog/big-post/big-post.component";
import {AccountEditComponent} from "./components/authentication/account-edit/account-edit.component"
import { ListOfRoutesComponent } from './components/list-of-routes/list-of-routes.component';
import { TripComponent } from './components/trip/trip/trip.component';
import { TripInfoComponent } from './components/trip/trip-info/trip-info.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'create-place', component: CreatePostPlaceComponent },
  { path: 'like', component: LikeComponent },
  { path: 'account', component: AccountComponent },
  { path: 'account_edit', component: AccountEditComponent },
  { path: 'create_place', component: CreatePostPlaceComponent },
  { path: 'create_post', component: CreatePostComponent },
  { path: 'routes/create', component: CreateRouteComponent },
  { path: 'create_route', component: CreateRouteComponent },
  { path: 'sing_up', component: SignupComponent },
  { path: 'list_of_users', component: ListOfUsersComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'blog/:id', component: BlogComponent },
  { path: 'about', component: AboutePageComponent },
  { path: 'create_place/restaurant', component: CreateRestaurantComponent },
  { path: 'create_place/hotel', component: CreateHotelComponent },
  { path: 'single_page', component: BigPostComponent },
  { path: 'routes', component: ListOfRoutesComponent },
  { path: 'routes/:id', component: RouteComponent },
  { path: 'trip', component: TripComponent },
  { path: 'trip_info', component: TripInfoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
