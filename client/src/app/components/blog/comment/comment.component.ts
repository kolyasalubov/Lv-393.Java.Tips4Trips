import {Component, Input, OnInit} from '@angular/core';
import {CommentService} from './comment.service';
import {Comment} from '../../../model/comment.model';
import {AccountInfo} from '../../../model/account-info.model';
import {NgForm} from '@angular/forms';
import {LittlepostModel} from '../../../model/littlepost.model';


@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input() postid: number;
  @Input() countOfLikes: number;

  constructor(private commentService: CommentService) {
  }

  account: AccountInfo = new AccountInfo();
  noComment = false;
  oneComment = false;
  moreComment = false;


  commentProfile: Comment = new Comment(null, this.account, null, new Date(), null, 1, null);
  commentList: Comment[];

  onSubmit(f: NgForm) {
    console.log(f);
    this.commentService.createComment(this.commentProfile).subscribe(item => console.log(item));
    this.commentList.push(this.commentProfile);
    f.resetForm();
  }

  ngOnInit() {

    this.account.id = 1;
    this.account.firstName = 'Mota';
    this.account.lastName = 'Yurii';

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
