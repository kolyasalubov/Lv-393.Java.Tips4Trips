import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Like} from "../../../model/like.model";
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class LikeService {


  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/posts/';

  changeLike(postId: number, accountId: number): Observable<number> {
    return this.http.post<number>(this.baseUrl + postId + '/likes/change/' + accountId,{postId, accountId} );
  };

  createLike(postId: number, accountId: number): Observable<Like> {
    return this.http.post<Like>(this.baseUrl + postId + '/likes/create/' + accountId, {postId, accountId});
  };

  deleteLike(postId: number, accountId: number): Observable<Like> {
    return this.http.delete<Like>(this.baseUrl + postId + '/likes/delete/' + accountId, {})
  }
}
