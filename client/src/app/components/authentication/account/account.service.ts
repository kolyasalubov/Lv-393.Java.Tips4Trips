import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Account} from "../../../model/account.model";
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../../model/api.response";



@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:8080/account/';

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>('http://localhost:8080/'+ 'token/generate-token', loginPayload)

};

findById(id: number): Observable<ApiResponse> {
  return this.http.get<ApiResponse>(this.baseUrl + id);
}

createAccount(account: Account): Observable<ApiResponse> {
  return this.http.post<ApiResponse>(this.baseUrl, account);
}

update(account: Account): Observable<ApiResponse> {
  return this.http.put<ApiResponse>(this.baseUrl + account.id, account);
}
}
