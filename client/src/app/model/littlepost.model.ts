export class LittlepostModel {
  id: number;
  date: number;
  month: string;
  year: number;
  name: String;
  description: String;

  constructor(id: number,
              date: number,
              month: string,
              year: number,
              name: String,
              description: String) {
    this.id = id;
    this.date = date;
    this.month = month;
    this.year = year;
    this.name = name;
    this.description = description;
  }
}
