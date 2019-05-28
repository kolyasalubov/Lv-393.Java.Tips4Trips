import {Component, Input, OnInit} from '@angular/core';
import {FeedbackPlaceModel} from "../../../../model/feedback-place.model";
import {AccountDTO} from "../../../../model/account.model";
import {FeedbackPlaceService} from "../../feedback-place.service";
import {PlaceModel} from "../../../../model/place.model";
import {CustomAuthService} from "../../../authentication/custom-auth.service";

@Component({
  selector: 'app-feedback-place-create',
  templateUrl: './feedback-place-create.component.html',
  styleUrls: ['./feedback-place-create.component.css']
})
export class FeedbackPlaceCreateComponent implements OnInit {

  @Input() content: FeedbackPlaceModel[];
  @Input() placeId: number;

  feedbackPlaceModel: FeedbackPlaceModel;

  show = false;

  one: string;
  two: string;
  three: string;
  four: string;
  five: string;

  constructor(private service: FeedbackPlaceService, private authService: CustomAuthService) {
  }

  ngOnInit() {

    this.feedbackPlaceModel = new FeedbackPlaceModel();
    this.feedbackPlaceModel.creator = new AccountDTO(
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null, null, null, null, null);
    this.feedbackPlaceModel.place = new PlaceModel();
    this.feedbackPlaceModel.place.id = this.placeId;
    this.authService.getCurrentUser().subscribe(data => {
      this.feedbackPlaceModel.creator.id = data.id;
      this.service.check(this.placeId, this.feedbackPlaceModel.creator.id).subscribe(data1 => {
        this.show = !data1;
      });
    });
  }

  oneF() {
    this.clean();
    this.one = ' checked';
    this.feedbackPlaceModel.mark = 1;
  }

  twoF() {
    this.clean();
    this.oneF();
    this.two = ' checked';
    this.feedbackPlaceModel.mark = 2;
  }

  threeF() {
    this.clean();
    this.twoF();
    this.three = ' checked';
    this.feedbackPlaceModel.mark = 3;
  }

  fourF() {
    this.clean();
    this.threeF();
    this.four = ' checked';
    this.feedbackPlaceModel.mark = 4;
  }

  fiveF() {
    this.clean();
    this.fourF();
    this.five = ' checked';
    this.feedbackPlaceModel.mark = 5;
  }

  clean() {
    this.one = '';
    this.two = '';
    this.three = '';
    this.four = '';
    this.five = '';
  }

  save() {
    if (this.valid()) {
      this.service.create(this.feedbackPlaceModel).subscribe(data => {
        window.location.reload();
        console.log(data);
      });
    }
  }

  valid(): boolean {
    return this.feedbackPlaceModel.comment != null &&
      this.feedbackPlaceModel.comment !== '' &&
      this.feedbackPlaceModel.creator.id > 0 &&
      this.feedbackPlaceModel.mark < 6 &&
      this.feedbackPlaceModel.mark > 0;
  }

}
