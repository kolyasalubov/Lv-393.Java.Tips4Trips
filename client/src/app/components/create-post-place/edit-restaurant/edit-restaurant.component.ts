import {Component, OnInit} from '@angular/core';
import {Position} from "../../../model/position.model";
import {Restaurant} from "../../../model/restaurant.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CountryService} from "../../../country.service";
import {CityService} from "../city.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RestaurantService} from "../create-restaurant/restaurant.service";

@Component({
  selector: 'app-edit-restaurant',
  templateUrl: './edit-restaurant.component.html',
  styleUrls: ['./edit-restaurant.component.css']
})
export class EditRestaurantComponent implements OnInit {

  workingDays: string[] = ["MONDAY", "TUESDAY", "WEDNESDAY",
    "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];

  position: Position = new Position(0, 0);

  restaurant: Restaurant = new Restaurant(null, '', '', [], '',
    '', '', '', '', null, this.position,
    null,0,false);

  id: number;

  uploadPhoto: boolean = false;
  photoUrl: string = '';
  imageURLs: string[] = new Array();

  setCoordinates: Function = (x: number, y: number) => {
    this.position.coordinateX = x;
    this.position.coordinateY = y;
  }

  setAddress: Function = (address: string) => {
    this.restaurant.address = address;
  };

  formGroup: FormGroup = new FormGroup({
    name: new FormControl(null,[
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(35)
    ]),
    description: new FormControl(null,[
      Validators.required,
      Validators.minLength(10),
      Validators.maxLength(255)
    ]),
    workingDays: new FormControl(null, [
    ]),
    webSite: new FormControl(null,[
      Validators.required,
      Validators.pattern('^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$'),
      Validators.maxLength(35)
    ]),
    telephone: new FormControl(null,[
      Validators.required,
      Validators.pattern('\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n' +
        '2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n' +
        '4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$')
    ]),
    openingTime: new FormControl(null,[
      Validators.required
    ]),
    closingTime: new FormControl(null,[
      Validators.required
    ]),
    address: new FormControl(null,[
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(60)
    ]),
    averageBill: new FormControl(null,[
      Validators.required,
      Validators.min(10),
      Validators.max(999999)
    ]),
    positionX: new FormControl(null,[
      Validators.required
    ]),
    positionY: new FormControl(null,[
      Validators.required
    ]),
    hasVeganFood: new FormControl(),
  });

  constructor(private countryService: CountryService, private cityService: CityService, private router: Router,
              private restaurantService: RestaurantService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.restaurantService.findById(this.id).subscribe(data => {
      this.restaurant = data;
      this.position = data.position;
      this.imageURLs.push('http://localhost:8080/places/' + this.restaurant.id + '/image');
    });
  }

  update(){
    this.restaurant.position = this.position;
    this.restaurantService.update(this.restaurant).subscribe(data => {
      this.restaurant = data;
      this.photoUrl = 'http://localhost:8080/places/' + this.restaurant.id + '/image';
      this.uploadPhoto = true;
    });
    // setTimeout(() => {window.location.href = '/restaurants/' + this.restaurant.id;}, 2000);
  }

  setWorkingDays(day: string) {
    if (this.restaurant.workingDays.includes(day)) {
      this.restaurant.workingDays.splice(this.restaurant.workingDays.indexOf(day), 1);
    } else {
      this.restaurant.workingDays.push(day);
    }
  }

  navigate() {
    this.router.navigateByUrl('/restaurants/' + this.restaurant.id);
  }

}
