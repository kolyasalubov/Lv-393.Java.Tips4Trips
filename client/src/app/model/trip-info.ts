

export class TripInfoDTO {
    id: number;
    name: string;
    description: string;
    creationDate: string;
    startDate: string;
    self: string;

    constructor(id: number,name: string,description: string,creationDate: string,startDate: string,self: string) {
        this.id = id;    
        this.name = name;
         this.description = description;
         this.creationDate = creationDate;
          this.startDate = startDate;
         this.self = self;
}
}
