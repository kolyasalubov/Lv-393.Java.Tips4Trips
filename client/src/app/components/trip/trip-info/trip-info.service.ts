import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import { HttpClient } from '@angular/common/http';
import {FindGroupDetailsDTO} from "../../../model/trip-details";

@Injectable({
    providedIn: 'root'
  })
  export class TripInfoService {

    constructor(private http: HttpClient) { }
    baseUrl: string = 'http://localhost:8080/findgroups/';
  
     findById(id: number): Observable<FindGroupDetailsDTO> {
         return this.http.get<FindGroupDetailsDTO>(this.baseUrl + id);
       }


  }
