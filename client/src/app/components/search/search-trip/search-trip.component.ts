import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/service/search.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { TripInfoDTO } from 'src/app/model/trip-info';
import { TripSearchParams } from 'src/app/model/search/trip-search-params';

@Component({
  selector: 'app-search-trip',
  templateUrl: './search-trip.component.html',
  styleUrls: ['./search-trip.component.css']
})
export class SearchTripComponent {

  total: number;
  page: number;
  queryParams: object = {};
  trips: TripInfoDTO[] = [];
  params: TripSearchParams = new TripSearchParams();
  dateDropdownText: string = "Whenever";

  constructor(
    private searchService: SearchService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  init() {
    this.queryParams = this.route.snapshot.queryParams;
    this.params = new TripSearchParams();
    this.route.queryParams.subscribe(params => {
      let dateOption = params['dateOption'];
      if (dateOption == "Today") this.setTodayOption()
      else if (dateOption == "Last Week") this.setLastWeekOption()
      else if (dateOption == "Last Month") this.setLastMonthOption()
      else if (dateOption == "Last Year") this.setLastYearOption()
      else {
        if (params['startDate'] >= 0) {
          this.params.startDate = (params['startDate'] != "") ? new Date(Number(params['startDate'])) : null;
        }
        if (params['endDate'] >= 0) {
          this.params.endDate = (params['endDate'] != "") ? new Date(Number(params['endDate'])) : null;
        }
        this.dateDropdownText = this.formatDate(this.params.startDate) + ' - ' + this.formatDate(this.params.endDate);
      }
      if (this.params.startDate == null && this.params.endDate == null) this.setWheneverOption();
      this.params.routeName = params['routeName'];
      this.params.minSubscribersCount = params['minSubscribersCount'];
    });
    this.route.params.forEach((params: Params) => {
      try {
        this.page = (params['page']) ? +params['page'] -1 : 0 ;
      } catch (err) {
        this.page = 0;
      }
    });
  }

  search(seek: string, page: number): void {
    this.params.name = seek;
    this.searchService.findTripsByParams(this.params, page).subscribe(data => { 
      this.trips = data.content;
      this.total = data.totalPages;
    });
  }

  navigate(seek: string) : void {
    if (this.params == undefined) this.params = new TripSearchParams();
    let start: Number = (this.params.startDate != null) ? new Date(this.params.startDate).getTime() : null;
    let end: Number = (this.params.endDate != null) ? new Date(this.params.endDate).getTime() : null;
    this.router.navigate(['/search'], {
      queryParams: {
        seek: seek, category: 'trip',
        startDate: start,
        endDate: end,
        dateOption: this.dateDropdownText,
        routeName: this.params.routeName,
        minSubscribersCount: this.params.minSubscribersCount
      }
    });
  }

  setWheneverOption(): void {
    this.params.startDate = null;
    this.params.endDate = null;
    this.dateDropdownText = "Whenever";
  }

  setTodayOption(): void {
    let now: Date = new Date();
    this.params.startDate = now;
    this.params.endDate = now;
    this.dateDropdownText = "Today";

  }


  setLastWeekOption(): void {
    const DAYS_IN_WEEK = 7;
    let now: Date = new Date();
    let lastWeek: Date = new Date();
    lastWeek.setDate(now.getDate() - DAYS_IN_WEEK);
    this.params.endDate = now;
    this.params.startDate = lastWeek;
    this.dateDropdownText = "Last Week";
  }

  setLastMonthOption(): void {
    let now: Date = new Date();
    let lastMonth: Date = new Date();
    lastMonth.setMonth(now.getMonth() - 1);
    this.params.endDate = now;
    this.params.startDate = lastMonth;
    this.dateDropdownText = "Last Month";
  }

  setLastYearOption(): void {
    let now: Date = new Date();
    let lastYear: Date = new Date();
    lastYear.setFullYear(now.getFullYear() - 1);
    this.params.endDate = now;
    this.params.startDate = lastYear;
    this.dateDropdownText = "Last Year";
  }

  formatDate(date: any): string {
    if (date == null || date == "") return " ...";
    date = new Date(date);
    try {
      let formatted_date = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
      return formatted_date;
    } catch (err) {
      return " ...";
    }
  }
}
