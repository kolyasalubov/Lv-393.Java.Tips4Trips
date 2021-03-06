import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {BigPostModel} from '../../../model/big-post.model';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class BigPostService {

  constructor(private http: HttpClient) {
  }

  private urlPage = 'http://localhost:8080/posts/';


  getPost(page: number): Observable<BigPostModel> {
    return this.http.get<BigPostModel>(this.urlPage + page)
      .pipe(map(response => {
        return response;
      }));
  }
}
