import {Component, HostListener, Input, OnInit} from '@angular/core';
import {PageFeedbackPlaceModel} from "../../../../model/page-feedback-place.model";
import {CustomAuthService} from "../../../authentication/custom-auth.service";
import {FeedbackPlaceService} from "../../feedback-place.service";
import {FeedbackPlaceModel} from "../../../../model/feedback-place.model";

@Component({
  selector: 'app-list-place-feedback',
  templateUrl: './list-place-feedback.component.html',
  styleUrls: ['./list-place-feedback.component.css']
})
export class ListPlaceFeedbackComponent implements OnInit {

  @Input() id: number;

  feedbacks: FeedbackPlaceModel[];
  page = 1;
  last = false;

  constructor(private feedbackService: FeedbackPlaceService) {
  }

  onScrollDown() {
    if (!this.last) {
      this.feedbackService.getByPlaceIdAndPage(this.id, this.page).subscribe(data => {
        Array.prototype.push.apply(this.feedbacks, data.content);
        this.last = data.last;
        this.page = data.number + 2;
      });
    }
  }
  ngOnInit() {
    this.feedbackService.getByPlaceIdAndPage(this.id, this.page).subscribe(data => {
      this.feedbacks = data.content;
      this.last = data.last;
      this.page = 2;
    });
  }
}
