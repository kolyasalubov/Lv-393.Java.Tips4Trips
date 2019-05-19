import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from '@angular/common/http';
import {Message} from "../../model/message.model";


@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/messages/';

  getMessagesByChatId(id: number): Observable<Message[]> {
    return this.http.get<Message[]>(this.baseUrl + id);
  }

}


