import {City} from "./city.model";
import {Position} from "./position.model";

export class Restaurant {
  id: number;
  name: string;
  description: string;
  workingDays: string[];
  webSite: string;
  telephone: string;
  typeOfBuilding: string;
  openingTime: Date;
  closingTime: Date;
  address: string;
  position: Position;
  photoPath: string;
  city: City;
  averageBill: number;
  hasVeganFood: boolean;


  constructor(id: number, name: string, description: string, workingDays: string[], webSite: string, telephone: string,
              typeOfBuilding: string, openingTime: Date, closingTime: Date, address: string,
              position: Position, photoPath: string, city: City, averageBill: number, hasVeganFood: boolean) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.workingDays = workingDays;
    this.webSite = webSite;
    this.telephone = telephone;
    this.typeOfBuilding = typeOfBuilding;
    this.openingTime = openingTime;
    this.closingTime = closingTime;
    this.address = address;
    this.position = position;
    this.photoPath = photoPath;
    this.city = city;
    this.averageBill = averageBill;
    this.hasVeganFood = hasVeganFood;
  }
}
