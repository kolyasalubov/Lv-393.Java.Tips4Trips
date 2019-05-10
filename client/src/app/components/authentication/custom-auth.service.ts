import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SignInForm} from '../../model/authentication/signin-form.model';
import {SignUpForm} from '../../model/authentication/signup-form.model';
import {Account} from '../../model/account.model';
import { TokenStorageService } from './token/token-storage.service';
import {SIGNIN_URL, SIGNUP_URL, CURRENT_USER_URL, ACCESS_TOKEN, LOGOUT_URL} from '../../constants';

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

  getCurrentUser(): Observable<Account> {
    return this.http.get<Account>(CURRENT_USER_URL);
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
