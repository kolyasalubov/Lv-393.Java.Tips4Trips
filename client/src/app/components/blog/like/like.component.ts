import {Component, Input, OnInit} from '@angular/core';
import{LikeService} from './like.service';

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.css']
})
export class LikeComponent implements OnInit {
  @Input() postid: number;
  constructor(private likeService:LikeService) { }
  counter:number;
  changeLike(){
      this.likeService.changeLike(this.postid,1).subscribe(item=>this.counter=(item));
      console.log(this.counter);
    }


  ngOnInit() {
    this.likeService.countLikes(this.postid).subscribe(item=>this.counter=(item));
  }

}
