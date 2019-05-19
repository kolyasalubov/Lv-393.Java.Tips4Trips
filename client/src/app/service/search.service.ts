import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RouteInfo } from '../model/route-info.model';
import { RouteSearchParams } from '../model/search/route-search-params';
import { PostSearchParams } from '../model/search/post-search-params';
import { LittlepostModel } from '../model/littlepost.model';
import { TripSearchParams } from '../model/search/trip-search-params';
import { TripInfoDTO } from '../model/trip-info';
import { Page } from '../model/page';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/search/';

  findRoutesByParams(params: RouteSearchParams, page: number): Observable<Page<RouteInfo>> {
    return this.http.post<Page<RouteInfo>>(this.baseUrl + "routes" + "?page=" + page, params );
  }

  findPostsByParams(params: PostSearchParams, page: number): Observable<Page<LittlepostModel>> {
    return this.http.post<Page<LittlepostModel>>(this.baseUrl + "posts" + "?page=" + page, params );
  }

  findTripsByParams(params: TripSearchParams, page: number): Observable<Page<TripInfoDTO>> {
    return this.http.post<Page<TripInfoDTO>>(this.baseUrl + "trips" + "?page=" + page, params );
  }

}
