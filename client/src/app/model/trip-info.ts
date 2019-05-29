import {Image} from "./image.model";


export class TripInfoDTO {
    id: number;
    name: string;
    description: string;
    creationDate: string;
    startDate: string;
    self: string;
  image: Image;


  constructor(id: number, name: string, description: string, creationDate: string, startDate: string, self: string, image: Image) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.creationDate = creationDate;
    this.startDate = startDate;
    this.self = self;
    this.image = image;
  }
}
