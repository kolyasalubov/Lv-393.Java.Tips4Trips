import {Component, Input, OnInit} from '@angular/core';
import {AccountInfo} from '../../../model/account-info.model';
import {SubscribersService} from "./subscribers.service";
@Component({
  selector: 'app-subscribers',
  templateUrl: './subscribers.component.html',
  styleUrls: ['./subscribers.component.css']
})
export class SubscribersComponent implements OnInit {

  @Input() tripId: number;

  account: AccountInfo = new AccountInfo();

  subscribers: AccountInfo[];



  constructor(private subscribersService : SubscribersService) { }

  ngOnInit() {
    this.showSubscribers();
  }

  public showSubscribers() {

    this.subscribersService.findByTripId(this.tripId)
      .subscribe(data => this.subscribers = data);
    console.log(this.subscribers);
  }

}
