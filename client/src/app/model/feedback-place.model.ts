import {PlaceModel} from "./place.model";
import {AccountDTO} from "./account.model";

export class FeedbackPlaceModel {
  id: number;
  place: PlaceModel;
  creator: AccountDTO;
  date: Date;
  mark: number;
  comment: string

}
