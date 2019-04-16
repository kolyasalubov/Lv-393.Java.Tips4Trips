import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";

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
import { CreatePostComponent } from './components/create-post/create-post.component';
import { CreatePostPlaceComponent } from './components/create-post-place/create-post-place.component';
import { CreateRouteComponent } from './components/create-route/create-route.component';
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
import { httpInterceptorProviders } from './components/authentication/auth-interceptor';
import { ListOfRoutesComponent } from './list-of-routes/list-of-routes.component';
import { PaginationComponent } from './components/blog/pagination/pagination.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
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
    PaginationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule, 
    ReactiveFormsModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
