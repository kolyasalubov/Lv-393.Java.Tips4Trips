import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Like} from "../../../model/like.model";
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../../model/api.response";
@Injectable({
  providedIn: 'root'
})
export class LikeService {
  

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:8080/posts/';
  
  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>('http://localhost:8080/'+ 'token/generate-token', loginPayload)

    
};
// createLike(postId: number, accountId:number):Observable<ApiResponse>{
//   return this.http.post<ApiResponse>(this.baseUrl+postId+'/likes/create/'+accountId, postId, accountId);
// };
}
