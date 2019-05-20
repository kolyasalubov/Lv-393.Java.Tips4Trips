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
  last

  constructor(private feedbackService: FeedbackPlaceService) {
  }

  ngOnInit() {
    this.feedbackService.getByPlaceIdAndPage(this.id, this.page).subscribe(data => {
      this.feedbacks = data.content;
      this.last = data.last;
      this.page = this.page++;
    });
  }
  // @HostListener('scroll', ['$event'])
  // scrollHandler() {
  //   console.log(22222222222222222);
  // }

}
