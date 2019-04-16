import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PlaceInfo } from './model/place-info.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlaceService {

  constructor(private http:HttpClient) { }

  baseUrl: string = 'http://localhost:8080/places/';

  findByName(name:string): Observable<PlaceInfo[]> {
    return this.http.get<PlaceInfo[]>(this.baseUrl + "name/" + name);
  }
  
}
