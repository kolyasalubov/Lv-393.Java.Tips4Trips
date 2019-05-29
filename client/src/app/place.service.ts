import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PlaceInfo } from './model/place-info.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlaceService {

  constructor(private http:HttpClient) { }

  baseUrl: string = 'http://localhost:8080/';

  findByName(name:string): Observable<PlaceInfo[]> {
    return this.http.get<PlaceInfo[]>(this.baseUrl + "places/name/" + name);
  }

  getAllByCityId(cityId: number): Observable<PlaceInfo[]> {
    return this.http.get<PlaceInfo[]>(this.baseUrl + "cities/" + cityId + "/places");
  }

  getNamesContaining(name: string): Observable<string[]> {
    return this.http.get<string[]>(this.baseUrl + "places/names/" + name);
  }
}
