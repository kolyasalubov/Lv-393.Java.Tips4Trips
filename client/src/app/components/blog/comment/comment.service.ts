import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Comment} from '../../../model/comment.model';
import {Observable} from 'rxjs/index';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/comment';

  createComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(this.baseUrl + '/create', comment);
  }

  deleteComment( commentId: number, accountId):Observable<Comment> {
    console.log(commentId);
    console.log(accountId);
    return this.http.delete<Comment>(this.baseUrl +'/delete/' + accountId+'/'+commentId);
  }

  findByPostId(postId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.baseUrl + '/all/' + postId, {});
  }

}
