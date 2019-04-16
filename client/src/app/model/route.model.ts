import { RouteInfo } from './route-info.model'
import { PlaceInfo } from './place-info.model'
export class Route extends RouteInfo {
    photoPath: string;
    places: PlaceInfo[];
}
