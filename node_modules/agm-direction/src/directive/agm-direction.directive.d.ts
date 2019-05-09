import { OnChanges, OnInit, EventEmitter, OnDestroy } from '@angular/core';
import { GoogleMapsAPIWrapper } from '@agm/core';
import { InfoWindow } from '@agm/core/services/google-maps-types';
export declare class AgmDirection implements OnChanges, OnInit, OnDestroy {
    private gmapsApi;
    origin: any;
    destination: any;
    travelMode: String;
    transitOptions: any;
    drivingOptions: any;
    waypoints: any;
    optimizeWaypoints: Boolean;
    provideRouteAlternatives: Boolean;
    avoidHighways: Boolean;
    avoidTolls: Boolean;
    renderOptions: any;
    panel: object | undefined;
    markerOptions: {
        origin: any;
        destination: any;
        waypoints: any;
    };
    infoWindow: InfoWindow;
    visible: Boolean;
    renderRoute: any;
    onChange: EventEmitter<any>;
    onResponse: EventEmitter<any>;
    sendInfoWindow: EventEmitter<InfoWindow>;
    status: EventEmitter<string>;
    originDrag: EventEmitter<any>;
    destinationDrag: EventEmitter<any>;
    directionsService: any;
    directionsDisplay: any;
    private originMarker;
    private destinationMarker;
    private waypointsMarker;
    private isFirstChange;
    constructor(gmapsApi: GoogleMapsAPIWrapper);
    ngOnInit(): void;
    ngOnChanges(obj: any): void;
    ngOnDestroy(): void;
    /**
     * This event is fired when the user creating or updating this direction
     */
    private directionDraw;
    /**
     * Custom Origin and Destination Icon
     * @param map map
     * @param marker marker
     * @param markerOpts properties
     * @param content marker's infowindow content
     * @returns new marker
     * @memberof AgmDirection
     */
    private setMarker;
    /**
     * This event is fired when remove markers
     */
    private removeMarkers;
    /**
     * This event is fired when remove directions
     */
    private removeDirections;
    /**
     * This event is fired when destroy markers
     */
    private destroyMarkers;
}
