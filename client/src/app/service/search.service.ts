import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RouteInfo } from '../model/route-info.model';
import { RouteSearchParams } from '../model/search/route-search-params';
import { PostSearchParams } from '../model/search/post-search-params';
import { LittlepostModel } from '../model/littlepost.model';
import { TripSearchParams } from '../model/search/trip-search-params';
import { TripInfoDTO } from '../model/trip-info';

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

  findPostsByParams(params: PostSearchParams): Observable<LittlepostModel[]> {
    return this.http.post<LittlepostModel[]>(this.baseUrl + "posts", params );
  }

  findTripsByParams(params: TripSearchParams): Observable<TripInfoDTO[]> {
    return this.http.post<TripInfoDTO[]>(this.baseUrl + "trips", params );
  }

}
