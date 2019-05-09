/**
 * @fileoverview added by tsickle
 * @suppress {checkTypes,extraRequire,uselessCode} checked by tsc
 */
import { Directive, Input, Output, EventEmitter } from '@angular/core';
import { GoogleMapsAPIWrapper } from '@agm/core';
export class AgmDirection {
    /**
     * @param {?} gmapsApi
     */
    constructor(gmapsApi) {
        this.gmapsApi = gmapsApi;
        // Options
        this.travelMode = 'DRIVING';
        this.transitOptions = undefined;
        this.drivingOptions = undefined;
        this.waypoints = [];
        this.optimizeWaypoints = true;
        this.provideRouteAlternatives = false;
        this.avoidHighways = false;
        this.avoidTolls = false;
        // Remove or draw direction
        this.visible = true;
        // Direction change event handler
        this.onChange = new EventEmitter();
        // Direction response for the new request
        this.onResponse = new EventEmitter();
        // Send a custom infowindow
        this.sendInfoWindow = new EventEmitter();
        // Status of Directions Query (google.maps.DirectionsStatus.OVER_QUERY_LIMIT)
        this.status = new EventEmitter();
        // Marker drag event handler
        this.originDrag = new EventEmitter();
        this.destinationDrag = new EventEmitter();
        this.directionsService = undefined;
        this.directionsDisplay = undefined;
        this.waypointsMarker = [];
        // Use for visible flag
        this.isFirstChange = true;
    }
    /**
     * @return {?}
     */
    ngOnInit() {
        if (this.visible === true) {
            this.directionDraw();
        }
    }
    /**
     * @param {?} obj
     * @return {?}
     */
    ngOnChanges(obj) {
        /**
         * When visible is false then remove the direction layer
         */
        if (!this.visible) {
            try {
                this.removeMarkers();
                this.removeDirections();
            }
            catch (e) { }
        }
        else {
            if (this.isFirstChange) {
                /**
                 * When visible is false at the first time
                 */
                if (typeof this.directionsDisplay === 'undefined') {
                    this.directionDraw();
                }
                this.isFirstChange = false;
                return;
            }
            /**
             * When renderOptions are not first change then reset the display
             */
            if (typeof obj.renderOptions !== 'undefined') {
                if (obj.renderOptions.firstChange === false) {
                    this.removeMarkers();
                    this.removeDirections();
                }
            }
            this.directionDraw();
        }
    }
    /**
     * @return {?}
     */
    ngOnDestroy() {
        this.destroyMarkers();
        this.removeDirections();
    }
    /**
     * This event is fired when the user creating or updating this direction
     * @return {?}
     */
    directionDraw() {
        this.gmapsApi.getNativeMap().then((map) => {
            if (typeof this.directionsDisplay === 'undefined') {
                this.directionsDisplay = new google.maps.DirectionsRenderer(this.renderOptions);
                this.directionsDisplay.setMap(map);
                this.directionsDisplay.addListener('directions_changed', () => {
                    this.onChange.emit(this.directionsDisplay.getDirections());
                });
            }
            if (typeof this.directionsService === 'undefined') {
                this.directionsService = new google.maps.DirectionsService;
            }
            if (typeof this.panel === 'undefined') {
                this.directionsDisplay.setPanel(null);
            }
            else {
                this.directionsDisplay.setPanel(this.panel);
            }
            // Render exist direction
            if (typeof this.renderRoute === 'object' && this.renderRoute !== null) {
                this.directionsDisplay.setDirections(this.renderRoute);
                this.renderRoute = null; // or set undefined, ''
            }
            else {
                // Request new direction
                this.directionsService.route({
                    origin: this.origin,
                    destination: this.destination,
                    travelMode: this.travelMode,
                    transitOptions: this.transitOptions,
                    drivingOptions: this.drivingOptions,
                    waypoints: this.waypoints,
                    optimizeWaypoints: this.optimizeWaypoints,
                    provideRouteAlternatives: this.provideRouteAlternatives,
                    avoidHighways: this.avoidHighways,
                    avoidTolls: this.avoidTolls,
                }, (response, status) => {
                    this.onResponse.emit(response);
                    // Emit Query Status
                    this.status.emit(status);
                    /**
                     * DirectionsStatus
                     * https://developers.google.com/maps/documentation/javascript/directions#DirectionsStatus
                     */
                    switch (status) {
                        case 'OK':
                            this.directionsDisplay.setDirections(response);
                            /**
                             * Emit The DirectionsResult Object
                             * https://developers.google.com/maps/documentation/javascript/directions?hl=en#DirectionsResults
                             */
                            // Custom Markers
                            if (typeof this.markerOptions !== 'undefined') {
                                this.destroyMarkers();
                                // Set custom markers
                                /** @type {?} */
                                const _route = response.routes[0].legs[0];
                                try {
                                    // Origin Marker
                                    if (typeof this.markerOptions.origin !== 'undefined') {
                                        this.markerOptions.origin.map = map;
                                        this.markerOptions.origin.position = _route.start_location;
                                        this.originMarker = this.setMarker(map, this.originMarker, this.markerOptions.origin, _route.start_address);
                                        if (this.markerOptions.origin.draggable) {
                                            this.originMarker.addListener('dragend', () => {
                                                this.origin = this.originMarker.position;
                                                this.directionDraw();
                                                this.originDrag.emit(this.origin);
                                            });
                                        }
                                    }
                                    // Destination Marker
                                    if (typeof this.markerOptions.destination !== 'undefined') {
                                        this.markerOptions.destination.map = map;
                                        this.markerOptions.destination.position = _route.end_location;
                                        this.destinationMarker = this.setMarker(map, this.destinationMarker, this.markerOptions.destination, _route.end_address);
                                        if (this.markerOptions.destination.draggable) {
                                            this.destinationMarker.addListener('dragend', () => {
                                                this.destination = this.destinationMarker.position;
                                                this.directionDraw();
                                                this.destinationDrag.emit(this.destination);
                                            });
                                        }
                                    }
                                    // Waypoints Marker
                                    if (typeof this.markerOptions.waypoints !== 'undefined') {
                                        this.waypoints.forEach((waypoint, index) => {
                                            // If waypoints are not array then set all the same
                                            if (!Array.isArray(this.markerOptions.waypoints)) {
                                                this.markerOptions.waypoints.map = map;
                                                this.markerOptions.waypoints.position = _route.via_waypoints[index];
                                                this.waypointsMarker.push(this.setMarker(map, waypoint, this.markerOptions.waypoints, _route.via_waypoints[index]));
                                            }
                                            else {
                                                this.markerOptions.waypoints[index].map = map;
                                                this.markerOptions.waypoints[index].position = _route.via_waypoints[index];
                                                this.waypointsMarker.push(this.setMarker(map, waypoint, this.markerOptions.waypoints[index], _route.via_waypoints[index]));
                                            }
                                        }); // End forEach
                                    }
                                }
                                catch (err) {
                                    console.error('MarkerOptions error.', err);
                                }
                            }
                            break;
                        default:
                            // console.warn(status);
                            break;
                    } // End switch
                });
            }
        });
    }
    /**
     * Custom Origin and Destination Icon
     * \@memberof AgmDirection
     * @param {?} map map
     * @param {?} marker marker
     * @param {?} markerOpts properties
     * @param {?} content marker's infowindow content
     * @return {?} new marker
     */
    setMarker(map, marker, markerOpts, content) {
        if (typeof this.infoWindow === 'undefined') {
            this.infoWindow = new google.maps.InfoWindow({});
            this.sendInfoWindow.emit(this.infoWindow);
        }
        marker = new google.maps.Marker(markerOpts);
        marker.addListener('click', () => {
            /** @type {?} */
            const infowindoContent = typeof markerOpts.infoWindow === 'undefined' ? content : markerOpts.infoWindow;
            this.infoWindow.setContent(infowindoContent);
            this.infoWindow.open(map, marker);
        });
        return marker;
    }
    /**
     * This event is fired when remove markers
     * @return {?}
     */
    removeMarkers() {
        if (typeof this.originMarker !== 'undefined') {
            this.originMarker.setMap(null);
        }
        if (typeof this.destinationMarker !== 'undefined') {
            this.destinationMarker.setMap(null);
        }
        this.waypointsMarker.forEach((w) => {
            if (typeof w !== 'undefined') {
                w.setMap(null);
            }
        });
    }
    /**
     * This event is fired when remove directions
     * @return {?}
     */
    removeDirections() {
        this.directionsDisplay.setPanel(null);
        this.directionsDisplay.setMap(null);
        this.directionsDisplay = undefined;
    }
    /**
     * This event is fired when destroy markers
     * @return {?}
     */
    destroyMarkers() {
        // Remove origin markers
        try {
            if (typeof this.originMarker !== 'undefined') {
                google.maps.event.clearListeners(this.originMarker, 'click');
                if (this.markerOptions.origin.draggable) {
                    google.maps.event.clearListeners(this.originMarker, 'dragend');
                }
            }
            if (typeof this.destinationMarker !== 'undefined') {
                google.maps.event.clearListeners(this.destinationMarker, 'click');
                if (this.markerOptions.origin.draggable) {
                    google.maps.event.clearListeners(this.destinationMarker, 'dragend');
                }
            }
            this.waypointsMarker.forEach((w) => {
                if (typeof w !== 'undefined') {
                    google.maps.event.clearListeners(w, 'click');
                }
            });
            this.removeMarkers();
        }
        catch (err) {
            console.error('Can not reset custom marker.', err);
        }
    }
}
AgmDirection.decorators = [
    { type: Directive, args: [{
                selector: 'agm-direction',
            },] }
];
/** @nocollapse */
AgmDirection.ctorParameters = () => [
    { type: GoogleMapsAPIWrapper }
];
AgmDirection.propDecorators = {
    origin: [{ type: Input }],
    destination: [{ type: Input }],
    travelMode: [{ type: Input }],
    transitOptions: [{ type: Input }],
    drivingOptions: [{ type: Input }],
    waypoints: [{ type: Input }],
    optimizeWaypoints: [{ type: Input }],
    provideRouteAlternatives: [{ type: Input }],
    avoidHighways: [{ type: Input }],
    avoidTolls: [{ type: Input }],
    renderOptions: [{ type: Input }],
    panel: [{ type: Input }],
    markerOptions: [{ type: Input }],
    infoWindow: [{ type: Input }],
    visible: [{ type: Input }],
    renderRoute: [{ type: Input }],
    onChange: [{ type: Output }],
    onResponse: [{ type: Output }],
    sendInfoWindow: [{ type: Output }],
    status: [{ type: Output }],
    originDrag: [{ type: Output }],
    destinationDrag: [{ type: Output }]
};
if (false) {
    /** @type {?} */
    AgmDirection.prototype.origin;
    /** @type {?} */
    AgmDirection.prototype.destination;
    /** @type {?} */
    AgmDirection.prototype.travelMode;
    /** @type {?} */
    AgmDirection.prototype.transitOptions;
    /** @type {?} */
    AgmDirection.prototype.drivingOptions;
    /** @type {?} */
    AgmDirection.prototype.waypoints;
    /** @type {?} */
    AgmDirection.prototype.optimizeWaypoints;
    /** @type {?} */
    AgmDirection.prototype.provideRouteAlternatives;
    /** @type {?} */
    AgmDirection.prototype.avoidHighways;
    /** @type {?} */
    AgmDirection.prototype.avoidTolls;
    /** @type {?} */
    AgmDirection.prototype.renderOptions;
    /** @type {?} */
    AgmDirection.prototype.panel;
    /** @type {?} */
    AgmDirection.prototype.markerOptions;
    /** @type {?} */
    AgmDirection.prototype.infoWindow;
    /** @type {?} */
    AgmDirection.prototype.visible;
    /** @type {?} */
    AgmDirection.prototype.renderRoute;
    /** @type {?} */
    AgmDirection.prototype.onChange;
    /** @type {?} */
    AgmDirection.prototype.onResponse;
    /** @type {?} */
    AgmDirection.prototype.sendInfoWindow;
    /** @type {?} */
    AgmDirection.prototype.status;
    /** @type {?} */
    AgmDirection.prototype.originDrag;
    /** @type {?} */
    AgmDirection.prototype.destinationDrag;
    /** @type {?} */
    AgmDirection.prototype.directionsService;
    /** @type {?} */
    AgmDirection.prototype.directionsDisplay;
    /** @type {?} */
    AgmDirection.prototype.originMarker;
    /** @type {?} */
    AgmDirection.prototype.destinationMarker;
    /** @type {?} */
    AgmDirection.prototype.waypointsMarker;
    /** @type {?} */
    AgmDirection.prototype.isFirstChange;
    /** @type {?} */
    AgmDirection.prototype.gmapsApi;
}
//# sourceMappingURL=agm-direction.directive.js.map