import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import { HttpClient } from '@angular/common/http';
import {AccountInfo} from "../../../model/account-info.model";

@Injectable({
  providedIn: 'root'
})
export class SubscribersService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/trips/';

   findByTripId(id: number): Observable<AccountInfo[]> {
     return this.http.get<AccountInfo[]>(this.baseUrl + id + "/subscribers");
   }

  subscribeById(tripId: number, accountId: number): Observable<AccountInfo> {
    return this.http.put<AccountInfo>(this.baseUrl + tripId +'/subscribe/' + accountId,{},{});
  }

  unSubscribeById(tripId: number, accountId: number){
    return this.http.delete(this.baseUrl + tripId +'/unsubscribe/' + accountId,{});
  }

}
