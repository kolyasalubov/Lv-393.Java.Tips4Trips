import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import {Country} from "./model/country.model";


@Injectable({
  providedIn: 'root'
})
export class CountryService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/countries/';


  getAll(): Observable<Country[]> {
    return this.http.get<Country[]>(this.baseUrl);
  }

  getById(id: number): Observable<Country> {
    return this.http.get<Country>(this.baseUrl + id);
  }


}
