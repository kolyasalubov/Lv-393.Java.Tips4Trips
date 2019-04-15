import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-post-place',
  templateUrl: './create-post-place.component.html',
  styleUrls: ['./create-post-place.component.css']
})
export class CreatePostPlaceComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  private restaurant = false;
  private hotel = false;
  private monument = false;

  createRestaurant() {
    if (!this.restaurant) {
      this.restaurant = true;
    } else {
      this.restaurant = false;
    }
  }
  createHotel() {
    this.restaurant = true;
  }
  createMonument() {
    this.restaurant = true;
  }

}
