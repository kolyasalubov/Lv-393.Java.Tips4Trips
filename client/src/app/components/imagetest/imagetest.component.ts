import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-imagetest',
  templateUrl: './imagetest.component.html',
  styleUrls: ['./imagetest.component.css']
})
export class ImagetestComponent implements OnInit {

  imageURL: string = 'http://localhost:8181/posts/1/images';
  alreadyCreatedImages: string[] = null;
  type: string = 'POST';
  allowMultiple: Boolean = true;
  
  constructor() { }

  ngOnInit() {
  }

}
