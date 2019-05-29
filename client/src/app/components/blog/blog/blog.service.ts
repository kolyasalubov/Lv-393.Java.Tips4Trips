import {Injectable} from '@angular/core';
import {PagelittlepostModel} from '../../../model/pagelittlepost.model';
import {Observable} from 'rxjs';
import { HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BlogService {

  constructor(private http: HttpClient) { }

  private urlPage = 'http://localhost:8080/blog/';

  getPosts(page: number): Observable<PagelittlepostModel> {
    return this.http.get<PagelittlepostModel>(this.urlPage+page)
      .pipe(
        map(response => {
          return response;
        }));
  }

}
