import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BigPostService} from './big-post.service';
import {BigPostModel} from '../../../model/big-post.model';

@Component({
  selector: 'app-big-post',
  templateUrl: './big-post.component.html',
  styleUrls: ['./big-post.component.css']
})
export class BigPostComponent implements OnInit {

  constructor(private bigserv: BigPostService, private activatedRoute: ActivatedRoute) {
  }

  post: BigPostModel;
  id: number;
  imageURL: string = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/posts/images/';
  img: any[];

  getPageClient(page: number): void {
    this.bigserv.getPost(page)
      .subscribe(data => {
        this.post = data;
      }, err => window.location.href = 'http://test234324.s3-website.us-east-2.amazonaws.com/404');
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    if (isNaN(this.id) || this.id < 1) {
      this.id = 1;
    }
    this.getPageClient(this.id);
  }

}
