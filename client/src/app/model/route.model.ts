import {RouteInfo} from './route-info.model'
import {PlaceInfo} from './place-info.model'

export class Route extends RouteInfo {
  verified: boolean;
  places: PlaceInfo[];
  photoPath: string;
}
