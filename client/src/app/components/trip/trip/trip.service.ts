import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from '@angular/common/http';
import {FindGroupInfoDTO} from 'src/app/model/trip-info';
import {Route} from "../../../model/route.model";
import {FindGroupDetailsDTO} from "../../../model/trip-details";

@Injectable({
  providedIn: 'root'
})
export class TripService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/findgroups';

  getAll(): Observable<FindGroupInfoDTO[]> {
    return this.http.get<FindGroupInfoDTO[]>(this.baseUrl);
  }

  createTrip(trip:FindGroupDetailsDTO):Observable<FindGroupDetailsDTO>{
    return this.http.post<FindGroupDetailsDTO>(this.baseUrl + "create", trip);
  }

}
