import {Component, Input, OnInit} from '@angular/core';
import {NotificationCommentModel} from "../../../../model/notification-comment.model";

@Component({
  selector: 'app-comment-account',
  templateUrl: './comment-account.component.html',
  styleUrls: ['./comment-account.component.css']
})
export class CommentAccountComponent implements OnInit {

  @Input() comment: NotificationCommentModel;

  constructor() { }

  ngOnInit() {
  }

}
