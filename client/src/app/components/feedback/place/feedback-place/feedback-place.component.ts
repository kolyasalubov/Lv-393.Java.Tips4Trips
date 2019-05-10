import {Component, Input, OnInit} from '@angular/core';
import {FeedbackPlaceModel} from "../../../../model/feedback-place.model";
import {Account} from "../../../../model/account.model";
import {FeedbackPlaceService} from "../../feedback-place.service";
import {PlaceModel} from "../../../../model/place.model";

@Component({
  selector: 'app-feedback-place',
  templateUrl: './feedback-place.component.html',
  styleUrls: ['./feedback-place.component.css']
})
export class FeedbackPlaceComponent implements OnInit {

  @Input() feedbackPlaceModel: FeedbackPlaceModel;

  two: string;
  three: string;
  four: string;
  five: string;

  constructor() {
  }

  ngOnInit() {
    if (this.feedbackPlaceModel.mark > 1) {
      this.two = ' checked';
    }
    if (this.feedbackPlaceModel.mark > 2) {
      this.three = ' checked';
    }
    if (this.feedbackPlaceModel.mark > 3) {
      this.four = ' checked';
    }
    if (this.feedbackPlaceModel.mark > 5) {
      this.five = ' checked';
    }
  }

}
