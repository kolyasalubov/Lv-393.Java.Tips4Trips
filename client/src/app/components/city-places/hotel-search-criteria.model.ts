export class HotelSearchCriteria {
  cityId: number;
  worksAt: string;
  priceIn: number;


  constructor(cityId: number, worksAt: string, priceIn: number) {
    this.cityId = cityId;
    this.worksAt = worksAt;
    this.priceIn = priceIn;
  }
}
