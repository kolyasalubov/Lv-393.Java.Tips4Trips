import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {PostDetailsModel} from "../../model/postDetails.model";
import {PageNotificationComment} from "../../model/page-notification-comment";
import {PageNotificationTrip} from "../../model/page-notification-trip";
import {TripDetailsDTO} from "../../model/trip-details";
import {PostCreateModel} from "../../model/postCreate.model";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) {
  }

  private urlPage = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/notification/';
  private baseUrl = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/posts/';

  getComment(id: number,page:number): Observable<PageNotificationComment> {
    return this.http.get<PageNotificationComment>(this.urlPage + 'comment/' + id+'/'+page);
  }
  getLike(id: number,page:number): Observable<PageNotificationComment> {
    return this.http.get<PageNotificationComment>(this.urlPage + 'like/' + id+'/'+page);
  }
  getTrip(id: number,page:number): Observable<PageNotificationTrip> {
    return this.http.get<PageNotificationTrip>(this.urlPage + 'trip/' + id+'/'+page);
  }

  createTrip(post: PostCreateModel): Observable<PostCreateModel> {
    return this.http.post<PostCreateModel>(this.baseUrl + "create", post);
  }
}
