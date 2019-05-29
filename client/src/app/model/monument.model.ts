import {City} from "./city.model";
import {Position} from "./position.model";
import {Image} from "./image.model";

export class Monument {
  id: number;
  name: string;
  description: string;
  address: string;
  position: Position;
  image: Image;
  cityDTO: City;
  category: string;

  constructor(id: number, name: string, description: string, address: string,
              position: Position, image: Image, cityDTO: City) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.address = address;
    this.position = position;
    this.image = image;
    this.cityDTO = cityDTO;
    this.category = "HOTELS";
  }
}
