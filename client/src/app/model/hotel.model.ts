import{Position} from "./position.model"
import{City} from "./city.model"
export class Hotel {

    id: number;
    name: string;
    description: string;
    workingDays:string [];
    webSite: string;
    telephone: string;
    typeOfBuilding: string;
    openingTime: Date;
    closingTime: Date;
    address: string;
    position: Position;
    photoPath: string;
    city: City ;
    maximumPrice : number;
    minimumPrice : number;


    constructor( id: number, name: string, description: string, workingDays:string [],webSite: string,telephone: string,typeOfBuilding: string,openingTime: Date,closingTime: Date,address: string,position: Position,photoPath: string,city: City ,maximumPrice : number,minimumPrice : number,
       ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.workingDays = workingDays;
        this.webSite = webSite;
        this.telephone = telephone;
        this.typeOfBuilding = typeOfBuilding;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.address = address;
        this.position = position;
        this.photoPath = photoPath;
        this.city = city;
        this.maximumPrice = maximumPrice;
        this.minimumPrice = minimumPrice;
       
      }
}
