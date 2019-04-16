import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RouteInfo } from './model/route-info.model';
import { Observable } from 'rxjs';
import { Route } from './model/route.model';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:8080/routes/';

  findAll(): Observable<RouteInfo[]> {
    return this.http.get<RouteInfo[]>(this.baseUrl);
  }
  findById(id: number): Observable<Route> {
    return this.http.get<Route>(this.baseUrl + id);
  }
  createRoute(route: Route): Observable<Route> {
    route.authorInfo.id = 1;
    return this.http.post<Route>(this.baseUrl + "create", route);
  }

}
