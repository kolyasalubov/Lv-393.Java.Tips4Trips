import {Component, OnInit} from '@angular/core';
import {PagelittlepostModel} from '../../../model/pagelittlepost.model';
import {BlogService} from './blog.service';
import {ActivatedRoute} from '@angular/router';

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
  pagePost: PagelittlepostModel;

  getPageClient(page: number): void {
    this.blogserv.getPosts(page)
      .subscribe(data => {
        this.pagePost = data;
        this.max = this.pagePost.totalPages;
      });
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
