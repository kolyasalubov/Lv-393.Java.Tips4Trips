import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import { HttpClient } from '@angular/common/http';
import {AccountInfo} from "../../../model/account-info.model";

@Injectable({
  providedIn: 'root'
})
export class SubscribersService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:8080/trips/';

   findByTripId(id: number): Observable<AccountInfo[]> {
     return this.http.get<AccountInfo[]>(this.baseUrl + id + "/subscribers");
   }

  subscribeById(tripId: number, account: AccountInfo): Observable<AccountInfo> {
    return this.http.get<AccountInfo>(this.baseUrl + tripId +'/subscribe/' + account.id);
  }


}
