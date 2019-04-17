import {Component, OnInit} from '@angular/core';
import {LikeService} from './like.service';

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.css']
})
export class LikeComponent implements OnInit {

  constructor(private likeService: LikeService) {
  }

  counter: number;

  changeLike() {
    this.likeService.changeLike(1, 1).subscribe(item => this.counter = (item));
    console.log(this.counter);
  }


  ngOnInit() {
    this.likeService.changeLike(1, 1).subscribe(item => this.counter = (item));
  }

}
