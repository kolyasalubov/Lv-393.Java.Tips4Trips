import { Component, OnInit } from '@angular/core';
import{LikeService} from './like.service';

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.css']
})
export class LikeComponent implements OnInit {

  constructor(private likeService:LikeService) { }
  isLike:Boolean=false;
  addLike(){
      this.likeService.createLike(1,1).subscribe(item=>console.log(item));
    }
    deleteLike(){
      this.likeService.deleteLike(1,1).subscribe(item=>console.log(item));
    }
    changeLike(){
      
      if(this.isLike){
        this.deleteLike();
        this.isLike=false;
      }else{
        this.addLike();
        this.isLike=true;
      }
    }
  ngOnInit() {
   
  }

}
