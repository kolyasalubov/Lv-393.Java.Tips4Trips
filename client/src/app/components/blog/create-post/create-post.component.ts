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
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  routeName = '';
  post: PostCreateModel;
  url = 'http://localhost:8080/posts/';
  uploadPhoto = false;

  constructor(private routeService: RouteService, private postService: PostService,
              private authService: CustomAuthService, private router: Router) {
  }

  formGroup: FormGroup = new FormGroup({
    name: new FormControl(null, [
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(35)
    ]),
    content: new FormControl(null, [
      Validators.required,
      Validators.minLength(10),
      Validators.maxLength(510)
    ]),
    route: new FormControl(null, [
      Validators.required
    ])
  });

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
          console.log(result);
          this.url = this.url + this.post.id + '/images';
          this.uploadPhoto = true;
          // window.location.href = 'http://localhost:4200/post/' + this.post.id;
        }
      );
    }
  }

  navigate() {
    this.router.navigateByUrl('/post/' + this.post.id);
  }

  validate(): boolean {
    return this.post.routeInfo != null &&
      this.post.author.id != null;
  }

}
