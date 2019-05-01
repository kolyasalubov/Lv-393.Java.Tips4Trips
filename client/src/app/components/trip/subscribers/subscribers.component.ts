import {Component, Input, OnInit} from '@angular/core';
import {AccountInfo} from '../../../model/account-info.model';
import {SubscribersService} from "./subscribers.service";
import {AuthService} from "../../authentication/auth.service";
@Component({
  selector: 'app-subscribers',
  templateUrl: './subscribers.component.html',
  styleUrls: ['./subscribers.component.css']
})
export class SubscribersComponent implements OnInit {

  @Input() tripId: number;

  account: AccountInfo = new AccountInfo();

  subscribers: AccountInfo[];



  constructor(private subscribersService : SubscribersService, private  authService: AuthService) { }

  ngOnInit() {

    this.authService.getCurrentUser().subscribe(data => {
      console.log("current user");
      console.log(data);
      this.account.id = data.id;
      this.account.firstName = data.firstName;
      this.account.lastName = data.lastName;
    });

    this.showSubscribers();
  }


  public showSubscribers() {
    this.subscribersService.findByTripId(this.tripId)
      .subscribe(data => this.subscribers = data);
  }

  public subscribe(){
    console.log("trip Id = " + this.tripId + "acc Id = " + this.account.id);
    this.subscribersService.subscribeById(this.tripId, this.account).subscribe(item => this.subscribers.push(item));
  }

}
