import {PlaceModel} from "./place.model";
import {Account} from "./account.model";

export class FeedbackPlaceModel {
  id: number;
  place: PlaceModel;
  creator: Account;
  date: Date;
  mark: number;
  comment: string

}
