import { Component, OnInit, Input } from '@angular/core';
import { CityService } from '../city/city.service';
import { City } from '../../model/city.model';
import { Router } from '@angular/router';
import { ConfirmationDialogService } from '../confirmation-dialog/confirmation-dialog.service';

@Component({
  selector: 'app-city-list',
  templateUrl: './city-list.component.html',
  styleUrls: ['./city-list.component.css']
})
export class CityListComponent implements OnInit {
  cities: City[] = null;
  closeResult: string;

  constructor(private cityService: CityService,
    private router: Router,
    private confirmationDialogService: ConfirmationDialogService) { }

  ngOnInit() {
    this.cityService.getAll().subscribe(data => this.cities = data);
  }

  deleteById(id: number) {
    this.cityService.deleteById(id);
  }

  public openConfirmationDialog(city: City) {
    this.confirmationDialogService.confirm('Delete city', 'Do you really want to delete "'+city.name+'" city?')
      .then((confirmed) => {
        if (confirmed) {
          this.deleteById(city.id);
        }
      })
      .catch(() => console.log('User dismissed the dialog (e.g., by using ESC, clicking the cross icon, or clicking outside the dialog)'));
  }

}
