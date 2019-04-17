import { Component, OnInit } from '@angular/core';
import { CommentService } from './comment.service';
import {Comment} from "../../../model/comment.model";
import {AccountInfo} from "../../../model/account-info.model";
import {NgForm} from "@angular/forms";


@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  constructor(private commentService:CommentService) { }
 account: AccountInfo = new AccountInfo();
 
  commentProfile:Comment=new Comment(null,this.account, null,new Date(),null,1,null);
  commentList:Comment[];

  onSubmit(f: NgForm){
    this.commentService.createComment(this.commentProfile).subscribe(item=>console.log(item));
    this.commentList.push(this.commentProfile);
    f.resetForm();
  }
  ngOnInit() {
this.account.id=1;
this.account.lastName='sdsd';
this.account.firstName='adasd';
     this.commentService.findByPostId(1).subscribe(item=>this.commentList = item);
  }

}
