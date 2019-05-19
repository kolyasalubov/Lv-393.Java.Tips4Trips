import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountFeed} from "../../model/account-feed";
import {AccountDTO} from "../../model/account.model";

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) { }

  baseUrl: string = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/accounts/';

  findById(id: number): Observable<AccountFeed> {
    return this.http.get<AccountFeed>(this.baseUrl + id + '/news');
  }

}

