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
import { CommentComponent } from './components/blog/comment/comment.component';
import { ListOfRoutesComponent } from './components/list-of-routes/list-of-routes.component';
import {RestaurantDetailsComponent} from "./components/restaurant-details/restaurant-details.component";
import {CreateMonumentComponent} from "./components/create-post-place/create-monument/create-monument.component";
import {MonumentDetailsComponent} from "./components/monument-details/monument-details.component";
import { TripComponent } from './components/trip/trip/trip.component';
import { TripInfoComponent } from './components/trip/trip-info/trip-info.component';
import {TestComponent} from './components/test/test.component';
import { AuthGuard } from './components/authentication/guards/auth.guard';
import {HotelDetailsComponent} from "./components/hotel-details/hotel-details.component";
import {CityPlacesComponent} from "./components/city-places/city-places.component";
import { CityComponent} from './components/city/city.component';
import { CountryComponent} from './components/country/country.component';
import { ImageDetails } from './components/image-details/image-details.component';
import { ImageUploadFormComponent } from './components/image-upload-form/image-upload-form.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'create-place', component: CreatePostPlaceComponent },
  { path: 'like', component: LikeComponent },
  { path: 'account', component: AccountComponent, canActivate: [AuthGuard] },
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
  { path: 'post', component: BigPostComponent },
  { path: 'create_place/hotel', component: CreateHotelComponent },
  { path: 'create_place/monument', component: CreateMonumentComponent },
  { path: 'routes', component: ListOfRoutesComponent },
  { path: 'comment', component: CommentComponent },
  { path: 'restaurants/:id', component: RestaurantDetailsComponent },
  { path: 'monuments/:id', component: MonumentDetailsComponent },
  { path: 'hotels/:id', component: HotelDetailsComponent },
  { path: 'routes/:id', component: RouteComponent },
  { path: 'post/:id', component: BigPostComponent },
  { path: 'trip', component: TripComponent },
  { path: 'trip/:id', component: TripInfoComponent },
  { path: 'test', component: TestComponent },
  { path: 'city_places/:id', component: CityPlacesComponent},
  { path: 'city', component: CityComponent },
  { path: 'country', component: CountryComponent },
  { path: 'uploadimage', component: ImageUploadFormComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
