import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import { HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {PageAccountRoleModel} from "../../model/pageAccountRole.model";

@Injectable({
  providedIn: 'root'
})
export class ListUsersService {

  constructor(private http: HttpClient) { }

  private urlPage = 'http://localhost:8080/accounts/page/';

  getPosts(page: number): Observable<PageAccountRoleModel> {
    return this.http.get<PageAccountRoleModel>(this.urlPage+page)
      .pipe(
        map(response => {
          return response;
        }));
  }

}
