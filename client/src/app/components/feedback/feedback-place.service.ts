import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PageNotificationComment} from "../../model/page-notification-comment";
import {FeedbackPlaceModel} from "../../model/feedback-place.model";
import {PageFeedbackPlaceModel} from "../../model/page-feedback-place.model";

@Injectable({
  providedIn: 'root'
})
export class FeedbackPlaceService {

  constructor(private http: HttpClient) {
  }

  private urlPage = 'http://localhost:8080/feedback/';

  create(feedback: FeedbackPlaceModel): Observable<FeedbackPlaceModel> {
    console.log(feedback);
    return this.http.post<FeedbackPlaceModel>('http://localhost:8080/feedback/create', feedback);
  }

  getByPlaceIdAndPage(placeId: number, page: number): Observable<PageFeedbackPlaceModel> {
    return this.http.get<PageFeedbackPlaceModel>(this.urlPage + placeId + '/' + page);
  }
}
