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

  isSubscribed: boolean = false;

  constructor(private subscribersService: SubscribersService, private  authService: AuthService) {
  }

  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => {
      console.log("current user");
      console.log(data);
      this.account.id = data.id;
      this.account.firstName = data.firstName;
      this.account.lastName = data.lastName;
    });

    this.showSubscribers();
    setTimeout(() => {
      this.checkSubscription()
    }, 400)
  }


  public showSubscribers() {
    this.subscribersService.findByTripId(this.tripId)
      .subscribe(data => {
        this.subscribers = data;

      });
  }

  public checkSubscription() {
    console.log("in checkSubscription");
    if (this.subscribers.find(x => this.account.id === x.id)) {
      this.isSubscribed = true;
      console.log("exist");
    } else {
      this.isSubscribed = false;
      console.log("not exist");
    }
    console.log(this.subscribers);
  }


  public subscribe() {
    console.log("trip Id = " + this.tripId + "acc Id = " + this.account.id);
    //check whatever subscribers[] contains current user
    this.subscribersService.subscribeById(this.tripId, this.account.id).subscribe(item => {
      this.subscribers.push(item);
      this.isSubscribed = true;
    });
  }

  public unsubscribe() {
    console.log("trip Id = " + this.tripId + "acc Id = " + this.account.id);
    //check whatever subscribers[] contains current user
    this.subscribersService.unSubscribeById(this.tripId, this.account.id).subscribe(item => {
      this.subscribers
        .splice(this.subscribers.indexOf(this.account), 1);
      this.isSubscribed = false;
    });
  }

}
