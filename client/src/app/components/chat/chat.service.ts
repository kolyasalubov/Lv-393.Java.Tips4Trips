import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from '@angular/common/http';
import {TripInfoDTO} from 'src/app/model/trip-info';
import {ApiResponse} from "../../model/api.response";


@Injectable({
  providedIn: 'root'
})
export class TripService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/trips/';


  //getById(id: number): Observable<TripDetailsDTO> {
  //  return this.http.get<TripDetailsDTO>(this.baseUrl + id);
 // }

}


