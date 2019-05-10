import {Component, OnInit} from '@angular/core';
import {NewsService} from "./news.service";
import {CustomAuthService} from "../authentication/custom-auth.service";
import {AccountInfo} from "../../model/account-info.model";
import {AccountFeed} from "../../model/account-feed";
import {AccountFollowing} from "../../model/account-following";
import {PostDetailsModel} from "../../model/postDetails.model";
import {BigPostModel} from "../../model/big-post.model";
import {post} from "selenium-webdriver/http";
import {Image} from "../../model/image.model";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {

  constructor(private newsService: NewsService, private authService: CustomAuthService ) {
  }
  currentAccount:Account;
  account: AccountFeed;
  followingAccounts: AccountFollowing[];
  posts: BigPostModel[]=[];
  more: boolean=false;
  imageURL = 'http://localhost:8080/posts/images/';
  imageReady = false;
  readMore(){
    if(this.more==false){
      this.more=true;
    }else{
      this.more=false;
    }
  }
sortPosts(){
  this.posts.sort(function (a,b) {
    return a.id-b.id;
  }).reverse();
}
  //  myFunction(id:string) {
  //
  //   var dots = document.getElementById("dots"+id);
  //   var moreText = document.getElementById("more"+id);
  //   var btnText = document.getElementById("myBtn"+id);
  //
  //   if (dots.style.display === "none") {
  //     dots.style.display = "inline";
  //     btnText.innerHTML = "Read more";
  //     moreText.style.display = "none";
  //   } else {
  //     dots.style.display = "none";
  //     btnText.innerHTML = "Read less";
  //     moreText.style.display = "inline";
  //   }
  // }
allPosts(){
   for(let i=0;i<this.followingAccounts.length;i++) {
    console.log(this.followingAccounts[i].post);
    for(let j=0;j<this.followingAccounts[i].post.length;j++) {
  console.log(this.followingAccounts[i].post[j]);
      this.followingAccounts[i].post[j].lastName=this.followingAccounts[i].lastName;
      this.followingAccounts[i].post[j].firstName=this.followingAccounts[i].firstName;
      this.followingAccounts[i].post[j].accountId=this.followingAccounts[i].id;
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
         this.allPosts();
         this.sortPosts();
         this.imageReady = true;
       });
    });


  }

  getImageString(image: Image) {
    return this.imageURL + image.id;
  }

}
