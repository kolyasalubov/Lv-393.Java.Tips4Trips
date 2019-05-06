import {Component, OnInit} from '@angular/core';
import {NewsService} from "./news.service";
import {AuthService} from "../authentication/auth.service";
import {AccountInfo} from "../../model/account-info.model";
import {AccountFeed} from "../../model/account-feed";
import {AccountFollowing} from "../../model/account-following";
import {PostDetailsModel} from "../../model/postDetails.model";
import {BigPostModel} from "../../model/big-post.model";
import {post} from "selenium-webdriver/http";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {

  constructor(private newsService: NewsService, private authService: AuthService ) {
  }
  currentAccount:Account;
  account: AccountFeed;
  followingAccounts: AccountFollowing[];
  posts: BigPostModel[]=[];
sortPosts(){
  this.posts.sort(function (a,b) {
    return a.id-b.id;
  }).reverse();
}
showPosts(){
   for(let i=0;i<this.followingAccounts.length;i++) {
    console.log(this.followingAccounts[i].post);
    for(let j=0;j<this.followingAccounts[i].post.length;j++) {
  console.log(this.followingAccounts[i].post[j]);
      this.followingAccounts[i].post[j].lastName=this.followingAccounts[i].lastName;
      this.followingAccounts[i].post[j].firstName=this.followingAccounts[i].firstName;
      this.posts.push((this.followingAccounts[i].post[j]));
    }
  }
}
  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => {
      console.log(data);

       this.newsService.findById(data.id).subscribe(data => {
         console.log(data);
          this.account = data;
          this.followingAccounts = data.followingAccounts;
          console.log(this.followingAccounts);
         this.showPosts();
         this.sortPosts();
       });
    });


  }

}
