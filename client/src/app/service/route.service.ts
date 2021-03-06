import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RouteInfo} from '../model/route-info.model';
import {Observable} from 'rxjs';
import {Route} from '../model/route.model';
import {PlaceInfo} from "../model/place-info.model";
import { Page } from '../model/page';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/routes/';

  findAll(): Observable<RouteInfo[]> {
    return this.http.get<RouteInfo[]>(this.baseUrl);
  }

  findVerified(page: number): Observable<Page<RouteInfo>> {
    return this.http.get<Page<RouteInfo>>(this.baseUrl + "verified", {params: {page: '' + page}});
  }

  findNotVerified(page: number): Observable<Page<RouteInfo>> {
    return this.http.get<Page<RouteInfo>>(this.baseUrl + "notVerified", {params: {page: '' + page}});
  }

  findById(id: number): Observable<Route> {
    return this.http.get<Route>(this.baseUrl + id);
  }

  createRoute(route: Route): Observable<Route> {
    return this.http.post<Route>(this.baseUrl + "create", route);
  }
  updateRoute(route: Route): Observable<Route> {
    return this.http.put<Route>(this.baseUrl + "update", route);
  }
  deleteRoute(id: number): Observable<Route> {
    return this.http.delete<Route>(this.baseUrl + "delete/" + id);
  }
  verifyRoute(id: number): Observable<Route> {
    return this.http.put<Route>(this.baseUrl + id + "/verify", {});
  }
  findByName(name: string): Observable<Route> {
    return this.http.get<Route>(this.baseUrl + "name/" + name);
  }
  getNamesContaining(name: string): Observable<string[]> {
    return this.http.get<string[]>(this.baseUrl + "names/" + name);
  }
}
