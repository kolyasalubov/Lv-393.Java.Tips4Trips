import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthLoginInfo} from './login/login-info';
import {SignUpInfo} from './signup/signup-info';
import {Account} from '../../model/account.model';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { stringify } from '@angular/core/src/render3/util';
import { TokenStorageService } from './token/token-storage.service';


const httpOptions = {
  headers: new HttpHeaders(
    {'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = '//localhost:8080/authentication/signin';
  private signupUrl = '//localhost:8080/authentication/signup';
  private logoutUrl = '//localhost:8080/authentication/logout';
  currentUserUrl: string = 'http://localhost:8080/accounts/me';

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<any> {
    return this.http.post(this.loginUrl, credentials, {responseType : 'text'});
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }

  getCurrentUser(): Observable<Account> {
    return this.http.get<Account>(this.currentUserUrl);
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
    if(!this.checkLoggedUser){
      console.log("logout successed");
      return true;
    } else {
      console.log("logout failed");
      return false;
    }
}
}
