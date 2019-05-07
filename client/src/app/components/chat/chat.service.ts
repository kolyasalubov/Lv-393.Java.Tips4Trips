import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from '@angular/common/http';
import {TripInfoDTO} from 'src/app/model/trip-info';
import {ApiResponse} from "../../model/api.response";
import {Message} from "../../model/message.model";


@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/messages/messages/';

  getMessagesByChatId(id: number): Observable<Message[]> {
    return this.http.get<Message[]>(this.baseUrl + id);
  }

}


