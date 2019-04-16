import { Component, OnInit } from '@angular/core';
 
import { AuthService } from '../auth.service';
import { TokenStorageService } from '../token/token-storage.service';
import { AuthLoginInfo } from './login-info';

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
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;
 
  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }
 
  ngOnInit() {

  }
 
  onSubmit() {
 
    this.loginInfo = new AuthLoginInfo(
      this.loginForm.login,
      this.loginForm.password);
 
this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        console.log(data);
        console.log(data.token);
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUsername(data.login);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        
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
