import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from '@angular/common/http';
import {TripDetailsDTO} from "../../../model/trip-details";
import {TripInfoDTO} from 'src/app/model/trip-info';
import {ApiResponse} from "../../../model/api.response";

@Injectable({
  providedIn: 'root'
})
export class TripService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/trips/';

  getAll(): Observable<TripInfoDTO[]> {
    return this.http.get<TripInfoDTO[]>(this.baseUrl);
  }

  getById(id: number): Observable<TripDetailsDTO> {
    return this.http.get<TripDetailsDTO>(this.baseUrl + id);
  }

  createTrip(trip: TripDetailsDTO): Observable<TripDetailsDTO> {
    return this.http.post<TripDetailsDTO>(this.baseUrl + "create", trip);
  }

  update(trip: TripDetailsDTO): Observable<TripDetailsDTO> {
    return this.http.put<TripDetailsDTO>(this.baseUrl + "update", trip);
  }

  deleteTrip(id: number): Observable<TripDetailsDTO> {
    return this.http.delete<TripDetailsDTO>(this.baseUrl + 'delete/' + id);
  }
}


