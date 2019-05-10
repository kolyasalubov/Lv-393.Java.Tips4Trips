import { Component, OnInit, Input } from '@angular/core';
import { CountryService } from '../country/country.service';
import { Country } from '../../model/country.model';
import { Router } from '@angular/router';
import { ConfirmationDialogService } from '../confirmation-dialog/confirmation-dialog.service';

@Component({
  selector: 'app-country-list',
  templateUrl: './country-list.component.html',
  styleUrls: ['./country-list.component.css']
})
export class CountryListComponent implements OnInit {
  countries: Country[] = null;
  closeResult: string;

  constructor(private countryService: CountryService,
    private router: Router,
    private confirmationDialogService: ConfirmationDialogService) { }

  ngOnInit() {
    this.countryService.getAll().subscribe(data => this.countries = data);
  }

  deleteById(id: number) {
    this.countryService.deleteById(id);
  }

  public openConfirmationDialog(country: Country) {
    this.confirmationDialogService.confirm('Delete country', 'Do you really want to delete "'+country.name+'" country?')
      .then((confirmed) => {
        if (confirmed) {
          this.deleteById(country.id);
        }
      })
      .catch(() => console.log('User dismissed the dialog (e.g., by using ESC, clicking the cross icon, or clicking outside the dialog)'));
  }

}
