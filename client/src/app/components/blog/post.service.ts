import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {PostDetailsModel} from "../../model/postDetails.model";
import {PageNotificationComment} from "../../model/page-notification-comment";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) {
  }

  private urlPage = 'http://localhost:8080/notification/';

  getComment(id: number,page:number): Observable<PageNotificationComment> {
    return this.http.get<PageNotificationComment>(this.urlPage + 'comment/' + id+'/'+page);
  }
  getLike(id: number,page:number): Observable<PageNotificationComment> {
    return this.http.get<PageNotificationComment>(this.urlPage + 'like/' + id+'/'+page);
  }

}
