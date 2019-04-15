export class Account {
    id: number;
    firstName: string;
    lastName: string;
    phoneNumber: string;
    email: string;
    registrationDate: Date;
    role: string;
    about: string;

    constructor(id: number, firstName: string, lastName: string, phoneNumber: string, email: string, registrationDate: Date, role: string, about: string ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.registrationDate = registrationDate;
        this.role = role;
        this.about = about;
      }
}
