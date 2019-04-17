import { Position } from "./position.model";

export class Country {
  id: number;
  name: string;
  position: Position;

  constructor(id: number, name: string, position: Position) {
    this.id = id;
    this.name = name;
    this.position = position;
  }
}
