import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-post-place',
  templateUrl: './create-post-place.component.html',
  styleUrls: ['./create-post-place.component.css']
})
export class CreatePostPlaceComponent implements OnInit {

  createPlace: string;

  constructor() { }

  ngOnInit() {
  }

}
