export class AccountInfo {
    id: number;
    firstName: string;
    lastName: string;
    self: string;


  constructor(id: number, firstName: string, lastName: string, self: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.self = self;
  }
}
