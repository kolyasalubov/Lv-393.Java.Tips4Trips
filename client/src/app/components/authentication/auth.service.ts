import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthLoginInfo} from './login/login-info';
import {SignUpInfo} from './signup/signup-info';


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

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<any> {
    return this.http.post(this.loginUrl, credentials, {responseType : 'text'});
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }
}
