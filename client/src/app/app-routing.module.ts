import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { AccountComponent } from './components/authentication/account/account.component';
import { ProfileComponent } from './components/authentication/profile/profile.component';
import { RouteComponent } from './components/routes/route/route.component';
import { CreatePostComponent } from "./components/create-post/create-post.component";
import { CreatePostPlaceComponent } from "./components/create-post-place/create-post-place.component";
import { LikeComponent } from "./components/blog/like/like.component";
import { CreateRouteComponent } from "./components/routes/create-route/create-route.component";
import { SignupComponent } from "./components/authentication/signup/signup.component";
import { ListOfUsersComponent } from "./components/list-of-users/list-of-users.component";
import { BlogComponent } from "./components/blog/blog/blog.component";
import { AboutePageComponent } from "./components/aboute-page/aboute-page.component";
import { CreateRestaurantComponent } from "./components/create-post-place/create-restaurant/create-restaurant.component";
import { CreateHotelComponent } from "./components/create-post-place/create-hotel/create-hotel.component";
import { BigPostComponent } from "./components/blog/big-post/big-post.component";
import { AccountEditComponent } from "./components/authentication/account-edit/account-edit.component"
import { CommentComponent } from './components/blog/comment/comment.component';
import { RoutesPageComponent } from './components/routes/routes-page/routes-page.component';
import { RestaurantDetailsComponent } from "./components/restaurant-details/restaurant-details.component";
import { CreateMonumentComponent } from "./components/create-post-place/create-monument/create-monument.component";
import { MonumentDetailsComponent } from "./components/monument-details/monument-details.component";
import { TripComponent } from './components/trip/trip/trip.component';
import { TripInfoComponent } from './components/trip/trip-info/trip-info.component';
import { TestComponent } from './components/test/test.component';
import { AuthGuard } from './components/authentication/guards/auth.guard';
import { HotelDetailsComponent } from "./components/hotel-details/hotel-details.component";
import { CityPlacesComponent } from "./components/city-places/city-places.component";
import { CityComponent } from './components/city/city.component';
import { CountryComponent } from './components/country/country.component';
import { CreateTripComponent } from './components/trip/create-trip/create-trip.component';
import { ChangeComponent } from "./components/trip/change/change.component";
import { ErrorComponent } from "./components/error/error.component";
import { EditRouteComponent } from './components/routes/edit-route/edit-route.component';
import { NotVerifiedRoutesPageComponent } from './components/routes/not-verified-routes-page/not-verified-routes-page.component';
import { SearchPlaceComponent } from './components/search/search-place/search-place.component';
import { SearchComponent } from './components/search/search/search.component';
import {NewsComponent} from "./components/news/news.component";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'sing_up', component: SignupComponent },
  { path: 'like', component: LikeComponent },
  { path: 'comment', component: CommentComponent },
  { path: 'account', component: AccountComponent, canActivate: [AuthGuard] },
  { path: 'account_edit', component: AccountEditComponent },
  { path: 'city', component: CityComponent },
  { path: 'country', component: CountryComponent },
  //  { path: 'create-place', component: CreatePostPlaceComponent },
  { path: 'create_place', component: CreatePostPlaceComponent },
  { path: 'create_place/restaurant', component: CreateRestaurantComponent },
  { path: 'restaurants/:id', component: RestaurantDetailsComponent },
  { path: 'create_place/hotel', component: CreateHotelComponent },
  { path: 'hotels/:id', component: HotelDetailsComponent },
  { path: 'create_place/monument', component: CreateMonumentComponent },
  { path: 'monuments/:id', component: MonumentDetailsComponent },
  { path: 'city_places/:id', component: CityPlacesComponent },
  { path: 'create_post', component: CreatePostComponent },
  { path: 'create_route', component: CreateRouteComponent },
  { path: 'trips/create', component: CreateRouteComponent },
  { path: 'list_of_users', component: ListOfUsersComponent },
  { path: 'list_of_users/:id', component: ListOfUsersComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'blog/:id', component: BlogComponent },
  { path: 'about', component: AboutePageComponent },
  { path: 'create_place/restaurant', component: CreateRestaurantComponent },
  { path: 'restaurants/:id', component: RestaurantDetailsComponent },
  { path: 'create_place/hotel', component: CreateHotelComponent },
  { path: 'hotels/:id', component: HotelDetailsComponent },
  { path: 'create_place/monument', component: CreateMonumentComponent },
  { path: 'monuments/:id', component: MonumentDetailsComponent },
  { path: 'routes', component: RoutesPageComponent },
  { path: 'routes/create', component: CreateRouteComponent },
  { path: 'routes/notverified', component: NotVerifiedRoutesPageComponent },
  { path: 'routes/:id', component: RouteComponent },
  { path: 'routes/:id/edit', component: EditRouteComponent },
  { path: 'post', component: BigPostComponent },
  { path: 'post/:id', component: BigPostComponent },
  { path: 'trip/page', component: TripComponent },
  { path: 'trip/page/:page', component: TripComponent },
  { path: 'trip/:id', component: TripInfoComponent },
  { path: 'create_trip', component: CreateTripComponent },
  { path: 'edit_trip/:id', component: ChangeComponent },
  { path: 'test', component: TestComponent },
  { path: 'profile/:id', component: ProfileComponent },
  { path: 'search', component: SearchComponent },
  { path: 'search/page/:page', component: SearchComponent },
  { path: '404', component: ErrorComponent },
  {path: 'news', component: NewsComponent},
  { path: '**', redirectTo: '/404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
