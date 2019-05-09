import {FeedbackPlaceModel} from "./feedback-place.model";

export class PageFeedbackPlaceModel {
  content: FeedbackPlaceModel[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  first: boolean;
  sort: string;
  number: number;
  numberOfElements: number;
}
