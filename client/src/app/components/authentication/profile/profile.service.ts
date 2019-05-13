import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountDTO} from "../../../model/account.model";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/accounts/';

  findById(id: number): Observable<AccountDTO> {
    return this.http.get<AccountDTO>(this.baseUrl + id);
  }

  subscribeTo(accountId:number, followingAccountId:number):Observable<AccountDTO>{
     return this.http.put<AccountDTO>(this.baseUrl + accountId+'/subscribe/' + followingAccountId,{});
  }
  unSubscribe(accountId:number, followingAccountId:number):Observable<AccountDTO>{
    return this.http.delete<AccountDTO>(this.baseUrl + accountId+'/unsubscribe/' + followingAccountId);
  }
  isFollowing(accountId:number, followingAccountId:number):Observable<boolean>{
    return this.http.get<boolean>(this.baseUrl + accountId + '/following/' + followingAccountId);
  }
  
}
