import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from '@angular/common/http';
import {TripDetailsDTO} from "../../../model/trip-details";

@Injectable({
  providedIn: 'root'
})
export class TripInfoService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/trips/';

  findById(id: number): Observable<TripDetailsDTO> {
    return this.http.get<TripDetailsDTO>(this.baseUrl + id);
  }
}
