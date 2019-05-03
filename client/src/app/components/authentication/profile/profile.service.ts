import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Account} from "../../../model/account.model";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/accounts/';

  findById(id: number): Observable<Account> {
    return this.http.get<Account>(this.baseUrl + id);
  }
  
}
