import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-error-message',
  templateUrl: './error-message.component.html',
  styleUrls: ['./error-message.component.css']
})
export class ErrorMessageComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute) {
  }

  message: string;

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.message = params.get('string');
    });
  }

}
