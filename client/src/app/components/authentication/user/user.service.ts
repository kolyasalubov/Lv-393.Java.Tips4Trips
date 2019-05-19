import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from "../../../model/user.model";
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../../model/api.response";
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  baseUrl: string = '//test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/users/';

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>('//test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/'+ 'token/generate-token', loginPayload)

};

findById(id: number): Observable<ApiResponse> {
  return this.http.get<ApiResponse>(this.baseUrl + id);
}

createUser(user: User): Observable<ApiResponse> {
  return this.http.post<ApiResponse>(this.baseUrl, user);
}

updateUser(user: User): Observable<ApiResponse> {
  return this.http.put<ApiResponse>(this.baseUrl + user.id, user);
}

deleteUser(id: number): Observable<ApiResponse> {
  return this.http.delete<ApiResponse>(this.baseUrl + id);
}
}
