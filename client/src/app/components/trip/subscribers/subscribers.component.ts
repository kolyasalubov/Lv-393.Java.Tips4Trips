import {Component, Input, OnInit} from '@angular/core';
import {AccountInfo} from '../../../model/account-info.model';
import {CustomAuthService} from "../../authentication/custom-auth.service";
import { SubscribersService } from './subscribers.service';

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

  constructor(private subscribersService: SubscribersService, private  authService: CustomAuthService) {
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
    if (this.subscribers.find(x => this.account.id === x.id)) {
      this.isSubscribed = true;
    } else {
      this.isSubscribed = false;
    }
    console.log(this.subscribers);
  }


  public subscribe() {
    console.log("subscribe : trip Id = " + this.tripId + "acc Id = " + this.account.id);
    //check whatever subscribers[] contains current user
    this.subscribersService.subscribeById(this.tripId, this.account.id).subscribe(item => {
      this.subscribers.push(item);
      this.isSubscribed = true;
    });
  }

  public unsubscribe() {
    console.log("unsubscribe: trip Id = " + this.tripId + "acc Id = " + this.account.id);
    //check whatever subscribers[] contains current user
    this.subscribersService.unSubscribeById(this.tripId, this.account.id).subscribe(item => {
      this.subscribers
        .splice(this.subscribers.indexOf(this.account), 1);
      this.isSubscribed = false;
    });
  }

  getSelfLink(accountInfo: AccountInfo): string {
    const url: string[] = accountInfo.self.replace("accounts","profile").split("/");
    return url[url.length-2] + "/" + url[url.length - 1];
  }

}
