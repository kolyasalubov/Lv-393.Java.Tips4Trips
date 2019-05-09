import { Component, OnInit } from '@angular/core';
import { RouteInfo } from 'src/app/model/route-info.model';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchService } from 'src/app/service/search.service';
import { RouteSearchParams } from 'src/app/model/search/route-search-params';

@Component({
  selector: 'app-search-route',
  templateUrl: './search-route.component.html',
  styleUrls: ['./search-route.component.css']
})
export class SearchRouteComponent {

  routes: RouteInfo[];
  params: RouteSearchParams = new RouteSearchParams();
  dateDropdownText: string = "Whenever";
  verifiedDropdownText: string = "Verified only";

  constructor(
    private searchService: SearchService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  init() {
    this.params = new RouteSearchParams();
    this.route.queryParams.subscribe(params => {
      this.params.verifiedOnly = params['verifiedOnly'] != "false";
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
      if (!this.params.verifiedOnly) this.verifiedDropdownText = "All";
    });
  }

  search(seek: string): void {
    this.params.name = seek;
    this.searchService.findRoutesByParams(this.params).subscribe(data => this.routes = data);
  }

  navigate(seek: string): void {
    if (this.params == undefined) this.params = new RouteSearchParams();
    let start: Number = (this.params.startDate != null) ? new Date(this.params.startDate).getTime() : null;
    let end: Number = (this.params.endDate != null) ? new Date(this.params.endDate).getTime() : null;
    this.router.navigate(['/search'], {
      queryParams: {
        seek: seek, category: 'route',
        verifiedOnly: this.params.verifiedOnly,
        startDate: start,
        endDate: end,
        dateOption: this.dateDropdownText
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
