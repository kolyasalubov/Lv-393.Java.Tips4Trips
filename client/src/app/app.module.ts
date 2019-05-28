import { BrowserModule } from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { AccountComponent } from './components/authentication/account/account.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { UserComponent } from './components/authentication/user/user.component';
import { AccountService } from './components/authentication/account/account.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AboutComponent } from './components/home/about/about.component';
import { HelpInfoComponent } from './components/home/help-info/help-info.component';
import { RecentStoriesComponent } from './components/home/recent-stories/recent-stories.component';
import { NewsSubscribeComponent } from './components/home/news-subscribe/news-subscribe.component';
import { CreatePostComponent } from './components/blog/create-post/create-post.component';
import { CreatePostPlaceComponent } from './components/create-post-place/create-post-place.component';
import { CreateRouteComponent } from './components/routes/create-route/create-route.component';
import { LikeComponent } from './components/blog/like/like.component';
import { SignupComponent } from './components/authentication/signup/signup.component';
import { ListOfUsersComponent } from './components/list-of-users/list-of-users.component';
import { BlogComponent } from './components/blog/blog/blog.component';
import { AboutePageComponent } from './components/aboute-page/aboute-page.component';
import { CreateRestaurantComponent } from './components/create-post-place/create-restaurant/create-restaurant.component';
import { CommentComponent } from './components/blog/comment/comment.component';
import { BigPostComponent } from './components/blog/big-post/big-post.component';
import { LittlePostComponent } from './components/blog/little-post/little-post.component';
import { LessPostComponent } from './components/blog/less-post/less-post.component';
import { CreateHotelComponent } from './components/create-post-place/create-hotel/create-hotel.component';
import { AccountEditComponent } from './components/authentication/account-edit/account-edit.component';
import { ListOfRoutesComponent } from './components/routes/list-of-routes/list-of-routes.component';
import { RouteComponent } from './components/routes/route/route.component';
import { httpInterceptorProviders } from './components/authentication/auth-interceptor';
import { PaginationComponent } from './components/blog/pagination/pagination.component';
import { TripComponent } from './components/trip/trip/trip.component';
import { TripInfoComponent } from './components/trip/trip-info/trip-info.component';
import { RestaurantDetailsComponent } from "./components/restaurant-details/restaurant-details.component";
import { CreateMonumentComponent } from "./components/create-post-place/create-monument/create-monument.component";
import { MonumentDetailsComponent } from "./components/monument-details/monument-details.component";
import { TestComponent } from './components/test/test.component';
import { HotelDetailsComponent } from './components/hotel-details/hotel-details.component';
import { CityPlacesComponent } from './components/city-places/city-places.component';
import { CityComponent } from './components/city/city.component';
import { CountryComponent } from './components/country/country.component';
import { ImageDetailsComponent } from './components/image-details/image-details.component';
import { ImageUploadFormComponent } from './components/image-upload-form/image-upload-form.component';
import { RouteInfoComponent } from './components/routes/route-info/route-info.component';
import { EditRouteComponent } from './components/routes/edit-route/edit-route.component';
import { CreateTripComponent } from './components/trip/create-trip/create-trip.component';
import { LittleRouteComponent } from './components/routes/little-route/little-route.component';
import { ChangeComponent } from './components/trip/change/change.component';
import { ErrorComponent } from './components/error/error.component';
import { ActiveTripComponent } from './components/authentication/account/active-trip/active-trip.component';
import { LikeAccountComponent } from './components/authentication/account/like-account/like-account.component';
import { CommentAccountComponent } from './components/authentication/account/comment-account/comment-account.component';
import { NewsComponent } from './components/news/news.component';
import { RoutesPageComponent } from './components/routes/routes-page/routes-page.component';
import { NotVerifiedRoutesPageComponent } from './components/routes/not-verified-routes-page/not-verified-routes-page.component';
import { SubscribersComponent } from './components/trip/subscribers/subscribers.component';
import { SearchComponent } from './components/search/search/search.component';
import { SearchRouteComponent } from './components/search/search-route/search-route.component';
import { ProfileComponent } from "./components/authentication/profile/profile.component";
import { ListPostsComponent } from './components/blog/list-posts/list-posts.component';
import { SearchPostComponent } from './components/search/search-post/search-post.component';
import { SearchTripComponent } from './components/search/search-trip/search-trip.component';
import { ListTripsComponent } from './components/trip/list-trips/list-trips.component';
import { EditHotelComponent } from './components/create-post-place/edit-hotel/edit-hotel.component';
import { EditRestaurantComponent } from './components/create-post-place/edit-restaurant/edit-restaurant.component';
import { EditMonumentComponent } from './components/create-post-place/edit-monument/edit-monument.component';
import { ImagetestComponent } from './components/imagetest/imagetest.component';
import { AgmCoreModule, GoogleMapsAPIWrapper } from '@agm/core';
import { MaptestComponent } from './components/maptest/maptest.component';
import { MapDisplayComponent } from './components/map/map-display/map-display.component';
import { MapPickerComponent } from './components/map/map-picker/map-picker.component';
import { ChatComponent } from './components/chat/chat.component';
import { FeedbackPlaceComponent } from './components/feedback/place/feedback-place/feedback-place.component';
import { FeedbackPlaceCreateComponent } from './components/feedback/place/feedback-place-create/feedback-place-create.component';
import { CountryListComponent } from './components/country-list/country-list.component';
import { CountryEditComponent } from './components/country-edit/country-edit.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { ConfirmationDialogService } from './components/confirmation-dialog/confirmation-dialog.service';
import { CityListComponent } from './components/city-list/city-list.component';
import { CityEditComponent } from './components/city-edit/city-edit.component';
import { CityRatingComponent } from './components/city-rating/city-rating.component';
import { CityRatingPageComponent } from './components/city-rating-page/city-rating-page.component';
import { BarRatingModule } from 'ngx-bar-rating'
import { SocialHandlerComponent } from './components/authentication/social-handler/social-handler.component';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PlaceInfoComponent } from './components/place-info/place-info.component';
import { SearchNavbarComponent } from './components/search/search-navbar/search-navbar.component';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material';
import {RouteMapComponent} from "./components/map/route-map/route-map.component";
import {AgmDirectionModule} from "agm-direction";
import { ForgotPasswordComponent } from './components/authentication/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './components/authentication/reset-password/reset-password.component';
import { MustMatchDirective } from './components/authentication/must-match.directive';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NewsSubscribeComponent,
    FooterComponent,
    HomeComponent,
    AccountComponent,
    LoginComponent,
    UserComponent,
    NavbarComponent,
    RecentStoriesComponent,
    AboutComponent,
    HelpInfoComponent,
    CreatePostComponent,
    CreatePostPlaceComponent,
    CreateRouteComponent,
    LikeComponent,
    SignupComponent,
    ListOfUsersComponent,
    BlogComponent,
    AboutePageComponent,
    CreateRestaurantComponent,
    CommentComponent,
    BigPostComponent,
    LittlePostComponent,
    LessPostComponent,
    CreateHotelComponent,
    AccountEditComponent,
    ListOfRoutesComponent,
    RestaurantDetailsComponent,
    CreateMonumentComponent,
    MonumentDetailsComponent,
    PaginationComponent,
    RouteComponent,
    TripComponent,
    TripInfoComponent,
    TestComponent,
    HotelDetailsComponent,
    CityPlacesComponent,
    CityComponent,
    CountryComponent,
    ImageDetailsComponent,
    ImageUploadFormComponent,
    RouteInfoComponent,
    EditRouteComponent,
    CreateTripComponent,
    LittleRouteComponent,
    ChangeComponent,
    ErrorComponent,
    ActiveTripComponent,
    LikeAccountComponent,
    CommentAccountComponent,
    CountryListComponent,
    CountryEditComponent,
    ConfirmationDialogComponent,
    CityListComponent,
    CityEditComponent,
    CityRatingComponent,
    CityRatingPageComponent,
    NewsComponent,
    RoutesPageComponent,
    NotVerifiedRoutesPageComponent,
    SubscribersComponent,
    SearchComponent,
    SearchRouteComponent,
    ProfileComponent,
    ListPostsComponent,
    SearchPostComponent,
    SearchTripComponent,
    ListTripsComponent,
    EditHotelComponent,
    EditRestaurantComponent,
    EditMonumentComponent,
    ImagetestComponent,
    MapDisplayComponent,
    MaptestComponent,
    MapPickerComponent,
    ChatComponent,
    FeedbackPlaceComponent,
    FeedbackPlaceCreateComponent,
    SocialHandlerComponent,
    PlaceInfoComponent,
    SearchNavbarComponent,
    RouteMapComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    MustMatchDirective
  ],
  imports: [
    BrowserModule,
    AgmCoreModule.forRoot({ apiKey: 'AIzaSyDGk8vnhsQ8XqNJRLCEfr1c4k7z1GsyvXE' }),
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    BrowserAnimationsModule,
    BarRatingModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatInputModule,
    AgmDirectionModule
  ],
  providers: [
    httpInterceptorProviders,
    GoogleMapsAPIWrapper,
    ConfirmationDialogService],
  entryComponents: [ConfirmationDialogComponent],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule {
}
