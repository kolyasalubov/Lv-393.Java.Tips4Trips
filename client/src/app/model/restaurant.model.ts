import {City} from "./city.model";
import {Position} from "./position.model";
import {WeekDay} from "@angular/common";

export class Restaurant {
  id: number;
  name: string;
  description: string;
  workingDays: string[];
  webSite: string;
  telephone: string;
  category: string;
  openingTime: string;
  closingTime: string;
  address: string;
  position: Position;
  photoPath: string;
  cityDTO: City;
  averageBill: number;
  hasVeganFood: boolean;


  constructor(id: number, name: string, description: string, workingDays: string[], webSite: string, telephone: string,
              openingTime: string, closingTime: string, address: string,
              position: Position, photoPath: string, cityDTO: City, averageBill: number, hasVeganFood: boolean) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.workingDays = workingDays;
    this.webSite = webSite;
    this.telephone = telephone;
    this.category = "RESTAURANTS";
    this.openingTime = openingTime;
    this.closingTime = closingTime;
    this.address = address;
    this.position = position;
    this.photoPath = photoPath;
    this.cityDTO = cityDTO;
    this.averageBill = averageBill;
    this.hasVeganFood = hasVeganFood;
  }
}
