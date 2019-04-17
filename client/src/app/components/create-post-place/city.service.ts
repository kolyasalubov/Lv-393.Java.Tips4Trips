import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import { City } from '../../model/city.model';


@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:8080/countries/1/cities/';


getAll(): Observable<City[]> {
  return this.http.get<City[]>(this.baseUrl);
}

getById(id: number): Observable<City> {
  return this.http.get<City>(this.baseUrl + id);
}

getAllByCountryId(id: number): Observable<City[]> {
  return this.http.get<City[]>("http://localhost:8080/countries/" + id + "/cities/");
}
}
