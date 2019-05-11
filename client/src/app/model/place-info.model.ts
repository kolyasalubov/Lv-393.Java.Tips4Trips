import {Image} from "./image.model";

export class PlaceInfo {
    id: number;
    name: string;
    description: string;
    self: string;
    category: string;
    position: Position;
    image: Image;
    imageUrl: string;
}
