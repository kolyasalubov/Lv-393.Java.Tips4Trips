import { Component, OnInit } from '@angular/core';
import {SwPush} from "@angular/service-worker";

@Component({
  selector: 'app-t',
  templateUrl: './t.component.html',
  styleUrls: ['./t.component.css']
})
export class TComponent implements OnInit {

  constructor(private swPush: SwPush
             ) { }

  ngOnInit() {
  }
  readonly VAPID_PUBLIC_KEY ="BOjdOnFBS1cvXemxa0UFODG-JFtd4PS438b7fNczVg8XMU-6coBk0JIKu13QowEenn0NHmZ4AidZg_ngYkFhvl4";
    subscribeToNotifications() {

    this.swPush.requestSubscription({
      serverPublicKey: this.VAPID_PUBLIC_KEY
    })
      .then(sub => alert('loool'))
      .catch(err => console.error("Could not subscribe to notifications", err));
  }

}
