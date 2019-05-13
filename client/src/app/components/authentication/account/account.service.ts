import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AccountDTO} from "../../../model/account.model";
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../../model/api.response";


@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/accounts/';


  findById(id: number): Observable<AccountDTO> {
    return this.http.get<AccountDTO>(this.baseUrl + id);
  }


  createAccount(account: AccountDTO): Observable<AccountDTO> {
    return this.http.post<AccountDTO>(this.baseUrl + "create", account);
  }

  updateAccount(myAccount: AccountDTO): Observable<AccountDTO> {
    console.log(myAccount);
    return this.http.put<AccountDTO>(this.baseUrl + "update", myAccount);
  }

  checkNotification(id:number): Observable<boolean> {
    console.log(this.baseUrl +id+ "/check");
    return this.http.get<boolean>(this.baseUrl +id+ "/check");
  }

  deleteAccount(id: number): Observable<AccountDTO> {
    return this.http.delete<AccountDTO>(this.baseUrl + "delete/" + id);
  }

  updateAccountRole(myAccount: AccountDTO): Observable<AccountDTO> {
    return this.http.put<AccountDTO>(this.baseUrl + "update/role", myAccount);
  }

  getCurrentUser(): Observable<AccountDTO> {
    return this.http.get<AccountDTO>(this.baseUrl + "me");
  }
}
