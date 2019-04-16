import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import { HttpClient } from '@angular/common/http';
import { FindGroupInfoDTO } from 'src/app/model/trip-info';

@Injectable({
    providedIn: 'root'
  })
  export class TripService {

    constructor(private http: HttpClient) { }
    baseUrl: string = 'http://localhost:8080/findgroups';
  

    
    getAll(): Observable<FindGroupInfoDTO[]> {
  return this.http.get<FindGroupInfoDTO[]>(this.baseUrl);
}

    // getTrips(): Observable<TripInfo[]> {

    //   return this.http.get<TripInfo[]>(this.baseUrl);
    //   //  .pipe(
    //  //     map(response => {
    //    //     const data = response;
    //   //      console.log(data.content);
    //   //      return data;
    //  //     }));
    // }
  }