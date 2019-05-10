import {AccountInfo} from "./account-info.model";

export class Image {
id: number;
name:string;
format:string;
uploadDate: Date;
creator :AccountInfo;

  constructor(id: number, name: string, format: string, uploadDate: Date, creator: AccountInfo) {
    this.id = id;
    this.name = name;
    this.format = format;
    this.uploadDate = uploadDate;
    this.creator = creator;
  }
}
