export class Position {
  coordinateX: number;
  coordinateY: number;

  constructor(coordinateX: number, coordinateY: number){
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
  }

  getX() {
    return this.coordinateX;
  }

  getY() {
    return this.coordinateY;
  }
}
