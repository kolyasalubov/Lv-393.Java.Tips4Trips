import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SignInForm} from '../../model/authentication/signin-form.model';
import {SignUpForm} from '../../model/authentication/signup-form.model';
import {AccountDTO} from '../../model/account.model';
import { TokenStorageService } from './token/token-storage.service';
import {SIGNIN_URL, SIGNUP_URL, CURRENT_USER_URL, ACCESS_TOKEN, 
  LOGOUT_URL, FORGOT_PASSWORD_URL, RESET_PASSWORD_URL} from '../../constants';
import { ForgotPasswordForm } from './forgot-password/forgot-password-form.model';
import { ResetPasswordForm } from './reset-password/reset-password-form.model';

@Injectable({
  providedIn: 'root'
})
export class CustomAuthService {

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
  }

  attemptAuth(credentials: SignInForm): Observable<any> {
    console.log(credentials);
    return this.http.post(SIGNIN_URL, credentials, {responseType : 'text'});
  }

  signUp(info: SignUpForm): Observable<any> {
    return this.http.post(SIGNUP_URL, info, {responseType : 'text'});
  }

  forgotPassword(info: ForgotPasswordForm): Observable<any> {
    return this.http.post(FORGOT_PASSWORD_URL, info);
  }

  resetPassword(info: ResetPasswordForm): Observable<any> {
    console.log(this.http.post(RESET_PASSWORD_URL, info));
    return this.http.post(RESET_PASSWORD_URL, info);
  }

  getCurrentUser(): Observable<AccountDTO> {
    return this.http.get<AccountDTO>(CURRENT_USER_URL);
  }
  
  checkLoggedUser(): boolean {
    if(this.tokenStorage.getToken()==null){
    return false;
    } else {
      return true;
    }
  }

  logout(): boolean {
    this.tokenStorage.signOut();
    this.http.get(LOGOUT_URL);
    if(!this.checkLoggedUser){
      console.log("logout successed");
      return true;
    } else {
      console.log("logout failed");
      return false;
    }
}
}
