import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-like-account',
  templateUrl: './like-account.component.html',
  styleUrls: ['./like-account.component.css']
})
export class LikeAccountComponent implements OnInit {

  @Input() accountPost: number;

  constructor() {
  }

  ngOnInit() {
    setTimeout(() => {    }, 100);
    console.log('tyt'+this.accountPost);
  }

}
