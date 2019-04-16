import { Component, OnInit } from '@angular/core';
import{LikeService} from './like.service';

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.css']
})
export class LikeComponent implements OnInit {

  constructor(private createLike:LikeService, private delLike:LikeService) { }
    addLike(){
      this.createLike.createLike(1,1).subscribe(item=>console.log(item));
    }
    deleteLike(){
      this.delLike.deleteLike(1,1).subscribe(item=>console.log(item));
    }
  ngOnInit() {
   
  }

}
