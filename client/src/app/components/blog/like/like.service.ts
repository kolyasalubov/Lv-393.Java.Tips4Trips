import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class LikeService {


  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/posts/';
 getAccounts(postId:number):Observable<Account[]>{
   return this.http.get<Account[]>(this.baseUrl + postId + '/likes');
 }
  changeLike(postId: number, accountId: number): Observable<void> {
    return this.http.post<void>(this.baseUrl + postId + '/likes/change/' + accountId,{postId, accountId} );
  };
  isAdded(postId: number, accountId: number): Observable<boolean> {
    return this.http.get<boolean>(this.baseUrl + postId + '/likes/exists/' + accountId );
  };
  countLikes(postId: number): Observable<number> {
    return this.http.get<number>(this.baseUrl + postId + '/likes/count' );
  };

  /*createLike(postId: number, accountId: number): Observable<Like> {
    return this.http.post<Like>(this.baseUrl + postId + '/likes/create/' + accountId, {postId, accountId});
  };

  deleteLike(postId: number, accountId: number): Observable<Like> {
    return this.http.delete<Like>(this.baseUrl + postId + '/likes/delete/' + accountId, {})
  }*/
}
