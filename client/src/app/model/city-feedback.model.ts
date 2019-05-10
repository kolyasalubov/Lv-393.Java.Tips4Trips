export class CityFeedback {
  cityId: number;
  content: string;
  weatherRating: number;
  safetyRating: number;
  transportRating: number;
  costOfLivingRating: number;
  entertainmentRating: number;

 
  constructor(cityId: number, content: string, weatherRating: number, safetyRating: number, 
    transportRating: number, costOfLivingRating: number, entertainmentRating: number){
    this.cityId = cityId;
    this.content = content;
    this.weatherRating = weatherRating;
    this.safetyRating = safetyRating;
    this.transportRating = transportRating;
    this.costOfLivingRating = costOfLivingRating;
    this.entertainmentRating = entertainmentRating;
  }
}
