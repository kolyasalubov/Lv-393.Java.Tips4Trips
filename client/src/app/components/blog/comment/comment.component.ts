import { Component, OnInit } from '@angular/core';
import { CommentService } from './comment.service';
import {Comment} from "../../../model/comment.model";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  constructor(private commentService:CommentService) { }
  findAll(){
    this.commentService.findByPostId(1).subscribe(item=>console.log(item));
  }
  commentProfile:Comment=new Comment(null,null,null,null,null);
  // onSubmit(){
  //   this.commentService.createComment(commentProfile:Comment).subscribe(item=>console.log(item));
  // }
  ngOnInit() {
    this.findAll();
  }

}
