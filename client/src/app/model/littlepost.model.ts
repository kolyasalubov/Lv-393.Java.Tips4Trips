import {Image} from "./image.model";

export class LittlepostModel {
  id: number;
  name: String;
  creationDate: Date;
  content: String;
  photoPath: String;
  images: Image[];
}
