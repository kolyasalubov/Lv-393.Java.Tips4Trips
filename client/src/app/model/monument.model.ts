import {City} from "./city.model";
import {Position} from "./position.model";

export class Monument {
  id: number;
  name: string;
  description: string;
  address: string;
  position: Position;
  photoPath: string;
  cityDTO: City;

  constructor(id: number, name: string, description: string, address: string,
              position: Position, photoPath: string, cityDTO: City) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.address = address;
    this.position = position;
    this.photoPath = photoPath;
    this.cityDTO = cityDTO;
  }
}
