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

  private urlPage = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/blog/';

  getPosts(page: number): Observable<PagelittlepostModel> {
    return this.http.get<PagelittlepostModel>(this.urlPage+page)
      .pipe(
        map(response => {
          return response;
        }));
  }

}
