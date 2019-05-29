import { Position } from "./position.model";

export class City {
  id: number;
  name: string;
  position: Position
  countryId: number;
  places: string;

  constructor(id: number, name: string, position: Position, countryId: number, places: string){
    this.id = id;
    this.name = name;
    this.position = position;
    this.countryId = countryId;
    this.places = places;
  }
}
