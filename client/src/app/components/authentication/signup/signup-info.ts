export class SignUpInfo {
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    email: string;
    numberPhone: string;
  
  
    constructor(username: string, password: string, firstName: string, lastName: string, email: string, numberPhone: string) {
      this.username = username;
      this.password = password;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.numberPhone = numberPhone;
    }
  }

