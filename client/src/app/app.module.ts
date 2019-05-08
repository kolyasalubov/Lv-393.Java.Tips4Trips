import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {HomeComponent} from './components/home/home.component';
import {AccountComponent} from './components/authentication/account/account.component';
import {LoginComponent} from './components/authentication/login/login.component';
import {UserComponent} from './components/authentication/user/user.component';
import {AccountService} from './components/authentication/account/account.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NavbarComponent} from './components/navbar/navbar.component';
import {AboutComponent} from './components/home/about/about.component';
import {HelpInfoComponent} from './components/home/help-info/help-info.component';
import {RecentStoriesComponent} from './components/home/recent-stories/recent-stories.component';
import {NewsSubscribeComponent} from './components/home/news-subscribe/news-subscribe.component';
import {CreatePostComponent} from './components/blog/create-post/create-post.component';
import {CreatePostPlaceComponent} from './components/create-post-place/create-post-place.component';
import {CreateRouteComponent} from './components/routes/create-route/create-route.component';
import {LikeComponent} from './components/blog/like/like.component';
import {SignupComponent} from './components/authentication/signup/signup.component';
import {ListOfUsersComponent} from './components/list-of-users/list-of-users.component';
import {BlogComponent} from './components/blog/blog/blog.component';
import {AboutePageComponent} from './components/aboute-page/aboute-page.component';
import {CreateRestaurantComponent} from './components/create-post-place/create-restaurant/create-restaurant.component';
import {CommentComponent} from './components/blog/comment/comment.component';
import {BigPostComponent} from './components/blog/big-post/big-post.component';
import {LittlePostComponent} from './components/blog/little-post/little-post.component';
import {LessPostComponent} from './components/blog/less-post/less-post.component';
import {CreateHotelComponent} from './components/create-post-place/create-hotel/create-hotel.component';
import {AccountEditComponent} from './components/authentication/account-edit/account-edit.component';
import {ListOfRoutesComponent} from './components/routes/list-of-routes/list-of-routes.component';
import {RouteComponent} from './components/routes/route/route.component';
import {httpInterceptorProviders} from './components/authentication/auth-interceptor';
import {PaginationComponent} from './components/blog/pagination/pagination.component';
import {TripComponent} from './components/trip/trip/trip.component';
import {TripInfoComponent} from './components/trip/trip-info/trip-info.component';
import {RestaurantDetailsComponent} from "./components/restaurant-details/restaurant-details.component";
import {CreateMonumentComponent} from "./components/create-post-place/create-monument/create-monument.component";
import {MonumentDetailsComponent} from "./components/monument-details/monument-details.component";
import {TestComponent} from './components/test/test.component';
import {HotelDetailsComponent} from './components/hotel-details/hotel-details.component';
import {CityPlacesComponent} from './components/city-places/city-places.component';
import {CityComponent} from './components/city/city.component';
import {CountryComponent} from './components/country/country.component';
import {ImageDetailsComponent} from './components/image-details/image-details.component';
import {ImageUploadFormComponent} from './components/image-upload-form/image-upload-form.component';
import {RouteInfoComponent} from './components/routes/route-info/route-info.component';
import {EditRouteComponent} from './components/routes/edit-route/edit-route.component';
import {CreateTripComponent} from './components/trip/create-trip/create-trip.component';
import {LittleRouteComponent} from './components/routes/little-route/little-route.component';
import {ChangeComponent} from './components/trip/change/change.component';
import {ErrorComponent} from './components/error/error.component';
import {ActiveTripComponent} from './components/authentication/account/active-trip/active-trip.component';
import {LikeAccountComponent} from './components/authentication/account/like-account/like-account.component';
import {CommentAccountComponent} from './components/authentication/account/comment-account/comment-account.component';
import {NewsComponent} from './components/news/news.component';
import {RoutesPageComponent} from './components/routes/routes-page/routes-page.component';
import {NotVerifiedRoutesPageComponent} from './components/routes/not-verified-routes-page/not-verified-routes-page.component';
import {SubscribersComponent} from './components/trip/subscribers/subscribers.component';
import {SearchPlaceComponent} from './components/search/search-place/search-place.component';
import {SearchComponent} from './components/search/search/search.component';
import {SearchRouteComponent} from './components/search/search-route/search-route.component';
import {ProfileComponent} from "./components/authentication/profile/profile.component";
import {ListPostsComponent} from './components/blog/list-posts/list-posts.component';
import {SearchPostComponent} from './components/search/search-post/search-post.component';
import {SearchTripComponent} from './components/search/search-trip/search-trip.component';
import {ListTripsComponent} from './components/trip/list-trips/list-trips.component';
import {ImagetestComponent} from './components/imagetest/imagetest.component';
import {AgmCoreModule, GoogleMapsAPIWrapper} from '@agm/core';
import {MaptestComponent} from './components/maptest/maptest.component';
import {MapDisplayComponent} from './components/map/map-display/map-display.component';
import {MapPickerComponent} from './components/map/map-picker/map-picker.component';
import {ChatComponent} from './components/chat/chat.component';
import { FeedbackPlaceComponent } from './components/feedback/place/feedback-place/feedback-place.component';
import { FeedbackPlaceCreateComponent } from './components/feedback/place/feedback-place-create/feedback-place-create.component';


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
    NewsComponent,
    RoutesPageComponent,
    NotVerifiedRoutesPageComponent,
    SubscribersComponent,
    SearchPlaceComponent,
    SearchComponent,
    SearchRouteComponent,
    ProfileComponent,
    ListPostsComponent,
    SearchPostComponent,
    SearchTripComponent,
    ListTripsComponent,
    ImagetestComponent,
    MapDisplayComponent,
    MaptestComponent,
    MapPickerComponent,

    ChatComponent,

    FeedbackPlaceComponent,

    FeedbackPlaceCreateComponent
  ],
  imports: [
    BrowserModule,
    AgmCoreModule.forRoot({apiKey: 'AIzaSyAMi90MHGDlEA-F6ktlRC36j1uhBBK9noo'}),
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    httpInterceptorProviders,
    GoogleMapsAPIWrapper
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
