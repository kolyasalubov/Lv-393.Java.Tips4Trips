import {Component, Input, OnInit} from '@angular/core';
import {CommentService} from './comment.service';
import {Comment} from '../../../model/comment.model';
import {AccountInfo} from '../../../model/account-info.model';
import {FormControl, FormGroup, NgForm, Validators} from '@angular/forms';

import {CustomAuthService} from "../../authentication/custom-auth.service";
import {Router} from "@angular/router";
import {Image} from "../../../model/image.model";


@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input() postid: number;
  @Input() countOfLikes: number;

  constructor(private commentService: CommentService, private  authService: CustomAuthService,
     private router: Router) {
  }

  account: AccountInfo = new AccountInfo();
  noComment = false;
  isButtonDisabled: boolean = false;
  isHidden:boolean=false;
  imageURL = 'http://localhost:8080/accounts/';

  commentProfile: Comment = new Comment(null, null, null, null, null, null, null);

  allComments: Comment[];
  formGroup: FormGroup = new FormGroup({
    description: new FormControl(null, [
      Validators.required,
      Validators.minLength(1),
      Validators.maxLength(500)
    ])
});
  public showComments() {

    this.commentService.findByPostId(this.postid)
      .subscribe(comments => this.allComments = comments);
  }

  deleteComment(id:number,comment:Comment){

    var isSubmit= confirm("Do you really want to delete a comment?");
    console.log(isSubmit);
    if (isSubmit){
      console.log(id);
      console.log(comment.accountInfo.id);
      this.commentService.deleteComment(id,this.account.id).subscribe(item=>{this.allComments
        .splice(this.allComments.indexOf(comment),1)});}

  }
  getImageString() {
    return this.imageURL + this.account.id +'/image';
  }
  onSubmit(f: NgForm) {
    this.isButtonDisabled = true;
    console.log(this.commentProfile.text);
    //setTimeout(()=>{ this.showComments() },400 )
    this.commentProfile.accountInfo = this.account;
    this.commentProfile.creationDate = new Date();
    this.commentProfile.postId = this.postid;
    console.log(this.account.imageId)
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
    this.showComments();
  }
}
