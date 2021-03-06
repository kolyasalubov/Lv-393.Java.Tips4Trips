import { Component, OnInit } from '@angular/core';
import {City} from "../../../model/city.model";
import {Country} from "../../../model/country.model";
import {Position} from "../../../model/position.model";
import {Monument} from "../../../model/monument.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CountryService} from "../../../country.service";
import {CityService} from "../city.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MonumentService} from "../create-monument/monument.service";

@Component({
  selector: 'app-edit-monument',
  templateUrl: './edit-monument.component.html',
  styleUrls: ['./edit-monument.component.css']
})
export class EditMonumentComponent implements OnInit {


  position: Position = new Position(0, 0);
  monument: Monument = new Monument(null, '', '', '', null, null, null);

  id: number;

  uploadPhoto: boolean = false;
  photoUrl: string = '';
  imageURLs: string[] = new Array();

  setCoordinates: Function = (x: number, y: number) => {
    this.position.coordinateX = x;
    this.position.coordinateY = y;
  }
  setAddress: Function = (address: string) => {
    this.monument.address = address;
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
    address: new FormControl(null,[
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(60)
    ]),
    positionX: new FormControl(null,[
      Validators.required
    ]),
    positionY: new FormControl(null,[
      Validators.required
    ])
  });

  constructor(private countryService: CountryService, private cityService: CityService, private router: Router,
              private monumentService: MonumentService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.monumentService.findById(this.id).subscribe(data => {this.monument = data;
      this.position = data.position;
      this.imageURLs.push('http://localhost:8080/places/' + this.monument.id + '/image');
    });
  }

  update(){
    this.monument.position = this.position;
    this.monumentService.update(this.monument).subscribe(data => {
      this.monument = data;
      this.photoUrl = 'http://localhost:8080/places/' + this.monument.id + '/image';
      this.uploadPhoto = true;
    });
  }

  navigate() {
    this.router.navigateByUrl('/monuments/' + this.monument.id);
  }

}
