export class Account {
  id: number;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  registrationDate: Date;
  role: string;
  about: string;
  newNotification: boolean;

  constructor(id: number, firstName: string, lastName: string, phoneNumber: string, email: string, registrationDate: Date, role: string, about: string, newNotification: boolean) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.registrationDate = registrationDate;
    this.role = role;
    this.about = about;
    this.newNotification = newNotification;
  }
}
