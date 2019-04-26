export class SignUpForm {
    login: string;
    firstName: string;
    lastName: string;
    phoneNumber: string;
    email: string;
    password: string;
    about: string;

    constructor(login: string, firstName: string, lastName: string, 
      phoneNumber: string, email: string, password: string, about: string ) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.about = about;
      }
}