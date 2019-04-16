import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RouteInfo } from './model/route-info.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:8080/routes/';

  findAll(): Observable<RouteInfo[]> {
    return this.http.get<RouteInfo[]>(this.baseUrl);
  }

}
