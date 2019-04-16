import { Component, OnInit } from '@angular/core';
import {LittlepostModel} from '../../../model/littlepost.model';

import {PagelittlepostModel} from '../../../model/pagelittlepost.model';
import {BlogService} from './blog.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {


  ppp: LittlepostModel =  new LittlepostModel(1,2,'asd',1,'asd','asdasd');
  constructor( private blogserv: BlogService) { }

  posts: LittlepostModel[];
  pagePost: PagelittlepostModel ;

  getPageClient(page: number): void {
    this.blogserv.getPosts(page)
      .subscribe(data => this.pagePost = data);
  }
  ngOnInit() {
    this.getPageClient(1);
  }

}
