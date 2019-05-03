import {NotificationTripModel} from "./notification-trip.model";

export class PageNotificationTrip {
  content: NotificationTripModel[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  first: boolean;
  sort: string;
  number: number;
  numberOfElements: number;
}
