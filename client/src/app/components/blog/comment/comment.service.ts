import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Comment} from "../../../model/comment.model";
import {Observable} from "rxjs/index";
@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:8080/posts/';

  createComment(comment:Comment):Observable<Comment>{
    return this.http.post<Comment>(this.baseUrl+comment.postId+'comments/create',{comment})
  }
}
