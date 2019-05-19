import {Component, OnInit} from '@angular/core';
import {RouteService} from "../../../service/route.service";
import {PostDetailsModel} from "../../../model/postDetails.model";
import {PostCreateModel} from "../../../model/postCreate.model";
import {PostService} from "../post.service";
import {CustomAuthService} from "../../authentication/custom-auth.service";
import {AccountDTO} from "../../../model/account.model";
import {ImageUploadFormComponent} from "../../image-upload-form/image-upload-form.component";
import {ImageService} from "../../../image.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  routeName: string;
  post: PostCreateModel;
  url: string = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/posts/';
  uploadPhoto: boolean = false;

  constructor(private routeService: RouteService, private postService: PostService,
              private authService: CustomAuthService, private router: Router,) {
  }

  ngOnInit() {
    this.post = new PostCreateModel();
    this.post.author = new AccountDTO(null,
      null,
      null,
      null,
      null,
      null,
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
      this.postService.createTrip(this.post).subscribe(result => {
          this.post = result;
          this.url = this.url + this.post.id + '/images';
          this.uploadPhoto = true;
          // window.location.href = 'http://test234324.s3-website.us-east-2.amazonaws.com/post/' + this.post.id;
        }
      );
    }
  }

  navigate() {
    this.router.navigateByUrl('/post/' + this.post.id);
  }

  validate(): boolean {
    return this.post.routeInfo != null &&
      this.post.name != null &&
      this.post.content != null &&
      this.post.author.id != null;
  }

}
