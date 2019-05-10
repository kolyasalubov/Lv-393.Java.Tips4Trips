import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountFeed} from "../../model/account-feed";
import {Account} from "../../model/account.model";

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) { }

  baseUrl: string = 'http://localhost:8080/accounts/';

  findById(id: number): Observable<AccountFeed> {
    return this.http.get<AccountFeed>(this.baseUrl + id + '/news');
  }

}

