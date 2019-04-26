import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { TokenStorageService } from '../token/token-storage.service';
import { UserAccount } from '../../../model/useraccount.model';
import { User } from '../../../model/user.model';
import { Account } from '../../../model/account.model';
import { Router } from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUpForm: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private signupInfo: UserAccount;
  private userInfo: User;
  private accountInfo: Account;

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.signUpForm.firstName);
    this.userInfo = new User(-1, this.signUpForm.login, this.signUpForm.password);
    this.accountInfo = new Account(-1, this.signUpForm.firstName,
      this.signUpForm.lastName, this.signUpForm.phoneNumber,
      this.signUpForm.email, null, null, this.signUpForm.about);
    
      this.signupInfo = new UserAccount(this.accountInfo, this.userInfo);
      this.authService.signUp(this.signupInfo).subscribe(
        data => {
      
          this.tokenStorage.saveToken(data);
          
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