import { Component, OnInit } from '@angular/core';
import { CustomAuthService } from '../custom-auth.service';
import { TokenStorageService } from '../token/token-storage.service';
import { SignInForm } from '../../../model/authentication/signin-form.model';
import { Router } from "@angular/router";
import {GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL} from '../../../constants';
import * as $ from 'jquery';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  private loginInfo: SignInForm;
 
  constructor(private authService: CustomAuthService, private tokenStorage: TokenStorageService,
    private router: Router) { }
 
  ngOnInit() {
    this.fullHeight(5);
  }
 
  onSubmit() {
 
    this.loginInfo = new SignInForm(
      this.loginForm.login,
      this.loginForm.password);
 
this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
      
        this.tokenStorage.saveToken(data);
        this.authService.getCurrentUser().subscribe(
          user => this.tokenStorage.saveAuthorities(user.role));

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.router.navigate(['account']).then(e => {
          if (e) {
            console.log("Navigation is successful!");
          } else {
            console.log("Navigation has failed!");
          }
        });
        
        },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }
 
  reloadPage() {
    window.location.href='/home';
  }

  googleSignIn(){
    window.location.href=GOOGLE_AUTH_URL;
  }

  facebookSignIn(){
    window.location.href=FACEBOOK_AUTH_URL;
  }

  fullHeight = function(divide) {
		$('.js-fullheight').css('height', $(window).height()/divide);
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height()/divide);
		});
    };
}
