export class RestaurantSearchCriteria {
  cityId: number;
  worksAt: string;
  averageBill: number;
  hasVeganFood: boolean;


  constructor(cityId: number, worksAt: string, averageBill: number, hasVeganFood: boolean) {
    this.cityId = cityId;
    this.worksAt = worksAt;
    this.averageBill = averageBill;
    this.hasVeganFood = hasVeganFood;
  }
}
