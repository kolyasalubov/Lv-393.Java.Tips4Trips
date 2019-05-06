import {Component, OnInit} from '@angular/core';
import {RouteService} from "../../../service/route.service";
import {PostDetailsModel} from "../../../model/postDetails.model";
import {PostCreateModel} from "../../../model/postCreate.model";
import {PostService} from "../post.service";
import {AuthService} from "../../authentication/auth.service";
import {Account} from "../../../model/account.model";

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  routeName: string;
  post: PostCreateModel;

  constructor(private routeService: RouteService, private postService: PostService, private authService: AuthService,) {
  }

  ngOnInit() {
    this.post = new PostCreateModel();
    this.post.author = new Account(null,
      null,
      null,
      null,
      null,
      null,
      null,
      null);

    this.authService.getCurrentUser().subscribe(data => {
      this.post.author.id = data.id;
    });
  }

  addRoute(): void {
    if (this.routeName == null) {
      return;
    }
    this.routeService.findByName(this.routeName).subscribe(data => {
      if (data != null) {
        this.post.routeInfo = data;
      }
    });
  }

  save(): void {
    if (this.validate()) {
      this.postService.createTrip(this.post).subscribe(result => this.post = result);
      setTimeout(() => {
        window.location.href = 'http://localhost:4200/post/' + this.post.id;
      }, 500);
    }
  }

  validate(): boolean {
    return this.post.routeInfo != null &&
      this.post.name != null &&
      this.post.content != null &&
      this.post.author.id != null;
  }

}
