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
  baseUrl: string = 'http://localhost:8080/accounts/';

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>('http://localhost:8080/'+ 'token/generate-token', loginPayload)

};

findById(id: number): Observable<Account> {
  return this.http.get<Account>(this.baseUrl + id);
}


createAccount(account: Account): Observable<Account> {
  return this.http.post<Account>(this.baseUrl + "create", account);
}

updateAccount(myAccount: Account): Observable<Account> {
  console.log(myAccount);
  return this.http.put<Account>(this.baseUrl + "update", myAccount);

}
}