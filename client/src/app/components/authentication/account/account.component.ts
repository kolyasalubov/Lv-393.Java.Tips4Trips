import {Component, OnInit} from '@angular/core';
import {Account} from 'src/app/model/account.model';
import {AccountService} from './account.service';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {PostService} from "../../blog/post.service";
import {PageNotificationComment} from "../../../model/page-notification-comment";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  accountProfile: Account;
  comments: PageNotificationComment;
  arecomments: boolean=false;
  likes: PageNotificationComment;
  arelikes: boolean=false;
  massage:string='you don\'t have yet';

  constructor(private accountService: AccountService,
              private authService: AuthService,
              private router: Router,
              private postService: PostService
  ) {
  }

  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => this.accountProfile = data);
    setTimeout(()=>{ this.getIdPosts();},500);
  }


  logout() {
    this.authService.logout();
    this.router.navigate(['home']);
  }

  initAcc(accountProfile: Account) {
    this.accountProfile = accountProfile;
  }


  editAccount() {
    this.router.navigate(['account_edit']);
  }

  getIdPosts() {
    this.postService.getComment(this.accountProfile.id,1).subscribe((data) => {
      this.comments=data;
      if(this.comments.content.length==0){
        this.arecomments=true
      }
    });
    this.postService.getLike(this.accountProfile.id,1).subscribe((data) => {
      this.likes=data;
      if(this.likes.content.length==0){
        this.arelikes=true
      }
    });
  }

}
