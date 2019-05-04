import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RouteInfo } from '../model/route-info.model';
import { RouteSearchParams } from '../model/search/route-search-params';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/search/';

  findRoutesByParams(params: RouteSearchParams): Observable<RouteInfo[]> {
    return this.http.post<RouteInfo[]>(this.baseUrl + "routes", params );
  }


}
