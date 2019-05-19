import {Component, OnInit} from '@angular/core';
import {PagelittlepostModel} from '../../../model/pagelittlepost.model';
import {BlogService} from './blog.service';
import {ActivatedRoute} from '@angular/router';
import {LittlepostModel} from "../../../model/littlepost.model";
import {Image} from "../../../model/image.model";

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {

  constructor(private blogserv: BlogService, private activatedRoute: ActivatedRoute) {
  }

  id: number;
  max: number;
  num: number;
  postP:  LittlepostModel[]=null;
  pagePost: PagelittlepostModel = null;

  getPageClient(page: number): void {
    this.blogserv.getPosts(page)
      .subscribe(data => {
        this.pagePost = data;
        this.max = this.pagePost.totalPages;
        this.num = this.pagePost.number;
        this.postP=this.pagePost.content;
      },err => window.location.href = 'http://localhost:4200/404');
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
      if (isNaN(this.id) || this.id < 1) {
        this.id = 1;
      }
      this.getPageClient(this.id);
    });
    
  }
  imageURL = 'http://localhost:8080/posts/images/';
  getImageString(image: Image) {
    return this.imageURL + image.id;
  }
}
