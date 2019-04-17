import {LikeService} from './like.service';
import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from '../../authentication/auth.service';

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.css']
})
export class LikeComponent implements OnInit {

  @Input() postid: number;
  accountId: number;

  constructor(private likeService: LikeService, private authService: AuthService) {
  }

  counter: number;

  changeLike() {

    this.likeService.changeLike(this.postid, this.accountId).subscribe(item => this.counter = (item));
    console.log(this.counter);
  }


  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => {
      this.accountId = data.id, console.log(data.id);
    });
    this.likeService.countLikes(this.postid).subscribe(item => this.counter = (item));
  }

}
