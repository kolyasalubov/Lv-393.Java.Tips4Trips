export class Country {
  id: number;
  name: string;
  position: Position;
  cities: string;

  constructor(id: number, name: string, position: Position, cities: string){
    this.id = id;
    this.name = name;
    this.position = position;
    this.cities = cities;
  }
}
