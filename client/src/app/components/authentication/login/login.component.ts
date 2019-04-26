import { Component, OnInit } from '@angular/core';
 
import { AuthService } from '../auth.service';
import { TokenStorageService } from '../token/token-storage.service';
import { SignInForm } from '../../../model/authentication/signin-form.model';
import { Router } from "@angular/router";

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
 
  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,
    private router: Router) { }
 
  ngOnInit() {

  }
 
  onSubmit() {
 
    this.loginInfo = new SignInForm(
      this.loginForm.login,
      this.loginForm.password);
 
this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
      
        this.tokenStorage.saveToken(data);
        this.authService.getCurrentUser().subscribe(
          user => this.tokenStorage.saveAuthorities(user.role))

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

}
