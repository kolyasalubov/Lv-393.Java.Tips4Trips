export class CityFeedback {
  cityId: number;
  content: string;
  creationDate: any;
  author: string;
  weatherRating: number;
  safetyRating: number;
  transportRating: number;
  costOfLivingRating: number;
  entertainmentRating: number;
  averageRating: number;

 
  constructor(cityId: number, content: string, creationDate: any, author: string, weatherRating: number, safetyRating: number, 
    transportRating: number, costOfLivingRating: number, entertainmentRating: number, averageRating: number){
    this.cityId = cityId;
    this.content = content;
    this.creationDate = creationDate;
    this.author = author;
    this.weatherRating = weatherRating;
    this.safetyRating = safetyRating;
    this.transportRating = transportRating;
    this.costOfLivingRating = costOfLivingRating;
    this.entertainmentRating = entertainmentRating;
    this.averageRating = averageRating;
  }
}
