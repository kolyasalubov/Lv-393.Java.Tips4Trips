import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/service/search.service';
import { Router } from '@angular/router';
import { LittlepostModel } from 'src/app/model/littlepost.model';
import { PostSearchParams } from 'src/app/model/search/post-search-params';

@Component({
  selector: 'app-search-post',
  templateUrl: './search-post.component.html',
  styleUrls: ['./search-post.component.css']
})
export class SearchPostComponent {

  posts: LittlepostModel[];
  params: PostSearchParams;

  constructor(
    private searchService: SearchService,
    private router: Router
  ) { }

  init() {
    this.params = new PostSearchParams();
  }
  
  search(seek: string): void {
    this.params.name = seek;
    this.searchService.findPostsByParams(this.params).subscribe(data => this.posts = data);
  }

  navigate(seek: string) : void {
    this.router.navigate(['/search'], { queryParams: { seek: seek, category: 'post' } });
  }
}
