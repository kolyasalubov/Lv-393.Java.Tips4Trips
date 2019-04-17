import {Component, Input, OnInit} from '@angular/core';
import {CommentService} from './comment.service';
import {Comment} from '../../../model/comment.model';
import {AccountInfo} from '../../../model/account-info.model';
import {NgForm} from '@angular/forms';
import {LittlepostModel} from '../../../model/littlepost.model';
import {AuthService} from '../../authentication/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input() postid: number;
  @Input() countOfLikes: number;

  constructor(private commentService: CommentService, private  authService: AuthService, private router: Router) {
  }

  account: AccountInfo = new AccountInfo();
  noComment = false;
  oneComment = false;
  moreComment = false;

  commentProfile: Comment = new Comment(null, null, null, null, null, null, null);


  commentList: Comment[];

  onSubmit(f: NgForm) {
    console.log(this.commentProfile.text);
    this.commentProfile.accountInfo = this.account;
    this.commentProfile.creationDate = new Date();
    this.commentProfile.postId = this.postid;
    this.commentService.createComment(this.commentProfile).subscribe(item => console.log(item));
    f.resetForm();
  }

  ngOnInit() {

    this.authService.getCurrentUser().subscribe(data => {
      console.log(data);
      this.account.id = data.id;
      this.account.firstName = data.firstName;
      this.account.lastName = data.lastName;
    });

    //if (this.countOfLikes > 1) {
    this.commentService.findByPostId(this.postid).subscribe(item => this.commentList = item);
    //   if (this.countOfLikes = 1) {
    //     this.oneComment = true;
    //   } else {
    //     this.moreComment = true;
    //   }
    // } else {
    //   this.noComment = true;
    // }
    // console.log(this.oneComment, this.moreComment, this.noComment);

  }

}
