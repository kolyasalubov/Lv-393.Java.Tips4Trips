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

  private url = 'http://localhost:8080/blog/';

  private urlPage = 'http://localhost:8080/blog/';

  getPosts(page: number): Observable<PagelittlepostModel> {
    let url = this.urlPage;
    url = url + page;
    return this.http.get<PagelittlepostModel>(url)
      .pipe(
        map(response => {
          const data = response;
          console.log(data.content);
          return data;
        }));
  }

}
