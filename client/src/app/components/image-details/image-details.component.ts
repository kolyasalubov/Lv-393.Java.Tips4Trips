import {Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-image-details',
  templateUrl: './image-details.component.html',
  styleUrls: ['./image-details.component.css']
})
export class ImageDetailsComponent implements OnInit {
  fileUpload: string;
  constructor() { }

  ngOnInit(fileUpload: string) {
    this.fileUpload = fileUpload;
  }

}
