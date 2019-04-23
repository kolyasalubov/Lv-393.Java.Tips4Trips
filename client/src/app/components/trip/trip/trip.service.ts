import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import { HttpClient } from '@angular/common/http';
import { TripInfoDTO } from 'src/app/model/trip-info';

@Injectable({
    providedIn: 'root'
  })
  export class TripService {

    constructor(private http: HttpClient) { }
    baseUrl: string = 'http://localhost:8080/trips';
  

    
    getAll(): Observable<TripInfoDTO[]> {
  return this.http.get<TripInfoDTO[]>(this.baseUrl);
}

  }
