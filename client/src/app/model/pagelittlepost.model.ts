import {LittlepostModel} from './littlepost.model';


export class PagelittlepostModel {
  content: LittlepostModel[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number ;
  first: boolean ;
  sort: string ;
  numberOfElements: number ;
}
