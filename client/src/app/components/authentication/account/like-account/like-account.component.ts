import {Component, Input, OnInit} from '@angular/core';
import {NotificationCommentModel} from "../../../../model/notification-comment.model";

@Component({
  selector: 'app-like-account',
  templateUrl: './like-account.component.html',
  styleUrls: ['./like-account.component.css']
})
export class LikeAccountComponent implements OnInit {

  @Input() like: NotificationCommentModel;

  constructor() {
  }

  ngOnInit() {}


}
