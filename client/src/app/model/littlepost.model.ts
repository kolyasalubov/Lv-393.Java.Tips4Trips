export class LittlepostModel {
  id: number;
  date: number;
  month: string;
  year: number;
  name: String;
  description: String;
  photoPath: String;

  constructor(id: number,
              date: number,
              month: string,
              year: number,
              name: String,
              photoPath: String) {
    this.id = id;
    this.date = date;
    this.month = month;
    this.year = year;
    this.name = name;
    this.photoPath = photoPath;
  }
}
