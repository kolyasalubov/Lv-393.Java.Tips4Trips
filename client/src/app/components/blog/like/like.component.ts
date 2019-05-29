import {Component, Input, OnInit} from '@angular/core';
import {LikeService} from './like.service';
import {CustomAuthService} from "../../authentication/custom-auth.service";

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.css']
})
export class LikeComponent implements OnInit {
  @Input() postid: number;
  accountId: number;
  isButtonDisabled: boolean = false;
  isAdded: boolean = false;
  counter: number;
  allAccounts: Account[];

  constructor(private likeService: LikeService,
              private authService: CustomAuthService) {
  }

  showAccounts() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
  }

  changeLike() {
    this.isButtonDisabled = true;
    this.likeService.changeLike(this.postid, this.accountId).subscribe(item => {
      this.likeService.isAdded(this.postid, this.accountId).subscribe(item => this.isAdded = item);
      this.likeService.countLikes(this.postid).subscribe(item => this.counter = (item));
      this.likeService.getAccounts(this.postid).subscribe(item => this.allAccounts = item);
    });
    this.isButtonDisabled = false;

  }


  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => {
      this.accountId = data.id, console.log(data.id);
      this.likeService.isAdded(this.postid, this.accountId).subscribe(item => this.isAdded = item);
    });
    this.likeService.getAccounts(this.postid).subscribe(item => this.allAccounts = item);
    this.likeService.countLikes(this.postid).subscribe(item => this.counter = (item));

  }

}
