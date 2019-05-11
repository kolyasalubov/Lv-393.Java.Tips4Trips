import {Image} from "./image.model";

export class Account {
  id: number;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  registrationDate: Date;
  role: string;
  //image: Image;
  about: string;
  newNotification: boolean;
    imageId: number;
    imageName: string;
    imageFormat: string;
    imageUploadDate: Date;


  constructor(id: number, firstName: string, lastName: string, phoneNumber: string, email: string, registrationDate: Date, role: string, about: string, newNotification: boolean, imageId: number, imageName: string, imageFormat: string, imageUploadDate: Date) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.registrationDate = registrationDate;
    this.role = role;
    this.about = about;
    this.newNotification = newNotification;
    this.imageId = imageId;
    this.imageName = imageName;
    this.imageFormat = imageFormat;
    this.imageUploadDate = imageUploadDate;
  }
}
