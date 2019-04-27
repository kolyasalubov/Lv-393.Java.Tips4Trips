import {Component, Input, OnInit} from '@angular/core';
import {CommentService} from './comment.service';
import {Comment} from '../../../model/comment.model';
import {AccountInfo} from '../../../model/account-info.model';
import {NgForm} from '@angular/forms';

import {AuthService} from "../../authentication/auth.service";
import {Router} from "@angular/router";


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
  isButtonDisabled: boolean = false;
  isHidden:boolean=false;

  commentProfile: Comment = new Comment(null, null, null, null, null, null, null);

  allComments: Comment[];

  public showComents() {

    this.commentService.findByPostId(this.postid)
      .subscribe(comments => this.allComments = comments);
  }

  deleteComment(id:number,comment:Comment){

    var isSubmit= confirm("Do you realy want to delete a comment?");
    console.log(isSubmit);
    if (isSubmit){
      console.log(id);
      console.log(comment.accountInfo.id);
      this.commentService.deleteComment(id,this.account.id).subscribe(item=>{this.allComments
        .splice(this.allComments.indexOf(comment),1)});}

  }

  onSubmit(f: NgForm) {
    this.isButtonDisabled = true;
    console.log(this.commentProfile.text);
    //setTimeout(()=>{ this.showComents() },400 )
    this.commentProfile.accountInfo = this.account;
    this.commentProfile.creationDate = new Date();
    this.commentProfile.postId = this.postid;
    this.commentService.createComment(this.commentProfile).subscribe(item => {
      this.allComments.push(item);
      f.resetForm();
      this.isButtonDisabled = false;

    });
  }

  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => {
      console.log(data);
      this.account.id = data.id;
      this.account.firstName = data.firstName;
      this.account.lastName = data.lastName;
    })
    this.showComents();
  }
}
