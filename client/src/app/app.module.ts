import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptor} from "./core/interceptor";

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
import { CreatePostRouteComponent } from './components/create-post-route/create-post-route.component';

import { LikeComponent } from './components/blog/like/like.component';

import { SingComponent } from './components/authentication/sing/sing.component';
import { ListOfUsersComponent } from './components/list-of-users/list-of-users.component';


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
    CreatePostRouteComponent,
    LikeComponent,
    SingComponent,
    ListOfUsersComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule, 
    ReactiveFormsModule
  ],
  providers: [AccountService, {provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi : true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
