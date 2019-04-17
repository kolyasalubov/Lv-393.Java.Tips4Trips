import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import { Hotel } from '../../../model/hotel.model';


@Injectable({
  providedIn: 'root'
})
export class HotelService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:8080/';
  //Todo: create method


}