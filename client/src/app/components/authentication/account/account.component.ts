import {Component, OnInit} from '@angular/core';
import {AccountDTO} from 'src/app/model/account.model';
import {AccountService} from './account.service';
import {Router} from '@angular/router';
import {CustomAuthService} from '../custom-auth.service';
import {PostService} from "../../blog/post.service";
import {PageNotificationComment} from "../../../model/page-notification-comment";
import {PageNotificationTrip} from "../../../model/page-notification-trip";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  accountProfile: AccountDTO;
  comments: PageNotificationComment;
  arecomments: boolean = false;
  likes: PageNotificationComment;
  arelikes: boolean = false;
  trip: PageNotificationTrip;
  areTrips: boolean = false;
  massage: string = 'you don\'t have yet';
  role: string;

  constructor(private accountService: AccountService,
              private authService: CustomAuthService,
              private router: Router,
              private postService: PostService
  ) {
  }

  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => {
      this.accountProfile = data;
      if (data.newNotification) {
        this.accountService.checkNotification(data.id).subscribe(() => {
        });
      }
      this.role = data.role;
    });

    setTimeout(() => {
      this.getNotific();
    }, 500);
  }


  logout() {
    this.authService.logout();
    this.router.navigate(['home']);
  }

  initAcc(accountProfile: AccountDTO) {
    this.accountProfile = accountProfile;
  }


  editAccount() {
    this.router.navigate(['account_edit']);
  }

  getNotific() {
    this.postService.getComment(this.accountProfile.id, 1).subscribe((data) => {
      this.comments = data;
      if (this.comments.content.length == 0) {
        this.arecomments = true
      }
    });
    this.postService.getLike(this.accountProfile.id, 1).subscribe((data) => {
      this.likes = data;
      if (this.likes.content.length == 0) {
        this.arelikes = true
      }
    });
    this.postService.getTrip(this.accountProfile.id, 1).subscribe((data) => {
      this.trip = data;
      if (this.trip.content.length == 0) {
        this.areTrips = true
      }
    });
  }
  getImageString() {
    return "http://localhost:8080/accounts/" + this.accountProfile.id + "/image";
  }

}
