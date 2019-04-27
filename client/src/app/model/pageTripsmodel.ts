import {TripInfoDTO} from "./trip-info";


export class PageTripsmodel {
  content: TripInfoDTO[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  first: boolean;
  sort: string;
  number: number;
  numberOfElements: number;
}
