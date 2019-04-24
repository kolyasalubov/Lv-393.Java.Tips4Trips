import {AccountRoleModel} from "./account-role.model";

export class PageAccountRoleModel {
  content: AccountRoleModel[];
  totalPages: number;
  last: boolean;
  size: number ;
  first: boolean ;
  sort: string ;
  number: number;
}
