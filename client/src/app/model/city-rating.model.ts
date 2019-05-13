import { Position } from "./position.model";

export class CityRating {
  id: number;
  name: string;
  position: Position
  countryId: number;
  places: string;
  averageRating: number;

  constructor(id: number, name: string, position: Position, countryId: number, places: string, averageRating: number){
    this.id = id;
    this.name = name;
    this.position = position;
    this.countryId = countryId;
    this.places = places;
    this.averageRating = averageRating;
  }
}
