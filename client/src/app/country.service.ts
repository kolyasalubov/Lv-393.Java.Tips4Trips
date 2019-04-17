import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import {Country} from "./model/country.model";


@Injectable({
  providedIn: 'root'
})
export class CountryService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:8080/countries/';


  getAll(): Observable<Country[]> {
    return this.http.get<Country[]>(this.baseUrl);
  }

  getById(id: number): Observable<Country> {
    return this.http.get<Country>(this.baseUrl + id);
  }


}
