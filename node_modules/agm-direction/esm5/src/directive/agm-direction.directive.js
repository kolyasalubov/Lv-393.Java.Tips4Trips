/**
 * @fileoverview added by tsickle
 * @suppress {checkTypes,extraRequire,uselessCode} checked by tsc
 */
import { Directive, Input, Output, EventEmitter } from '@angular/core';
import { GoogleMapsAPIWrapper } from '@agm/core';
var AgmDirection = /** @class */ (function () {
    function AgmDirection(gmapsApi) {
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
    AgmDirection.prototype.ngOnInit = /**
     * @return {?}
     */
    function () {
        if (this.visible === true) {
            this.directionDraw();
        }
    };
    /**
     * @param {?} obj
     * @return {?}
     */
    AgmDirection.prototype.ngOnChanges = /**
     * @param {?} obj
     * @return {?}
     */
    function (obj) {
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
    };
    /**
     * @return {?}
     */
    AgmDirection.prototype.ngOnDestroy = /**
     * @return {?}
     */
    function () {
        this.destroyMarkers();
        this.removeDirections();
    };
    /**
     * This event is fired when the user creating or updating this direction
     */
    /**
     * This event is fired when the user creating or updating this direction
     * @return {?}
     */
    AgmDirection.prototype.directionDraw = /**
     * This event is fired when the user creating or updating this direction
     * @return {?}
     */
    function () {
        var _this = this;
        this.gmapsApi.getNativeMap().then(function (map) {
            if (typeof _this.directionsDisplay === 'undefined') {
                _this.directionsDisplay = new google.maps.DirectionsRenderer(_this.renderOptions);
                _this.directionsDisplay.setMap(map);
                _this.directionsDisplay.addListener('directions_changed', function () {
                    _this.onChange.emit(_this.directionsDisplay.getDirections());
                });
            }
            if (typeof _this.directionsService === 'undefined') {
                _this.directionsService = new google.maps.DirectionsService;
            }
            if (typeof _this.panel === 'undefined') {
                _this.directionsDisplay.setPanel(null);
            }
            else {
                _this.directionsDisplay.setPanel(_this.panel);
            }
            // Render exist direction
            if (typeof _this.renderRoute === 'object' && _this.renderRoute !== null) {
                _this.directionsDisplay.setDirections(_this.renderRoute);
                _this.renderRoute = null; // or set undefined, ''
            }
            else {
                // Request new direction
                _this.directionsService.route({
                    origin: _this.origin,
                    destination: _this.destination,
                    travelMode: _this.travelMode,
                    transitOptions: _this.transitOptions,
                    drivingOptions: _this.drivingOptions,
                    waypoints: _this.waypoints,
                    optimizeWaypoints: _this.optimizeWaypoints,
                    provideRouteAlternatives: _this.provideRouteAlternatives,
                    avoidHighways: _this.avoidHighways,
                    avoidTolls: _this.avoidTolls,
                }, function (response, status) {
                    _this.onResponse.emit(response);
                    // Emit Query Status
                    _this.status.emit(status);
                    /**
                     * DirectionsStatus
                     * https://developers.google.com/maps/documentation/javascript/directions#DirectionsStatus
                     */
                    switch (status) {
                        case 'OK':
                            _this.directionsDisplay.setDirections(response);
                            /**
                             * Emit The DirectionsResult Object
                             * https://developers.google.com/maps/documentation/javascript/directions?hl=en#DirectionsResults
                             */
                            // Custom Markers
                            if (typeof _this.markerOptions !== 'undefined') {
                                _this.destroyMarkers();
                                // Set custom markers
                                /** @type {?} */
                                var _route_1 = response.routes[0].legs[0];
                                try {
                                    // Origin Marker
                                    if (typeof _this.markerOptions.origin !== 'undefined') {
                                        _this.markerOptions.origin.map = map;
                                        _this.markerOptions.origin.position = _route_1.start_location;
                                        _this.originMarker = _this.setMarker(map, _this.originMarker, _this.markerOptions.origin, _route_1.start_address);
                                        if (_this.markerOptions.origin.draggable) {
                                            _this.originMarker.addListener('dragend', function () {
                                                _this.origin = _this.originMarker.position;
                                                _this.directionDraw();
                                                _this.originDrag.emit(_this.origin);
                                            });
                                        }
                                    }
                                    // Destination Marker
                                    if (typeof _this.markerOptions.destination !== 'undefined') {
                                        _this.markerOptions.destination.map = map;
                                        _this.markerOptions.destination.position = _route_1.end_location;
                                        _this.destinationMarker = _this.setMarker(map, _this.destinationMarker, _this.markerOptions.destination, _route_1.end_address);
                                        if (_this.markerOptions.destination.draggable) {
                                            _this.destinationMarker.addListener('dragend', function () {
                                                _this.destination = _this.destinationMarker.position;
                                                _this.directionDraw();
                                                _this.destinationDrag.emit(_this.destination);
                                            });
                                        }
                                    }
                                    // Waypoints Marker
                                    if (typeof _this.markerOptions.waypoints !== 'undefined') {
                                        _this.waypoints.forEach(function (waypoint, index) {
                                            // If waypoints are not array then set all the same
                                            if (!Array.isArray(_this.markerOptions.waypoints)) {
                                                _this.markerOptions.waypoints.map = map;
                                                _this.markerOptions.waypoints.position = _route_1.via_waypoints[index];
                                                _this.waypointsMarker.push(_this.setMarker(map, waypoint, _this.markerOptions.waypoints, _route_1.via_waypoints[index]));
                                            }
                                            else {
                                                _this.markerOptions.waypoints[index].map = map;
                                                _this.markerOptions.waypoints[index].position = _route_1.via_waypoints[index];
                                                _this.waypointsMarker.push(_this.setMarker(map, waypoint, _this.markerOptions.waypoints[index], _route_1.via_waypoints[index]));
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
    };
    /**
     * Custom Origin and Destination Icon
     * @param map map
     * @param marker marker
     * @param markerOpts properties
     * @param content marker's infowindow content
     * @returns new marker
     * @memberof AgmDirection
     */
    /**
     * Custom Origin and Destination Icon
     * \@memberof AgmDirection
     * @param {?} map map
     * @param {?} marker marker
     * @param {?} markerOpts properties
     * @param {?} content marker's infowindow content
     * @return {?} new marker
     */
    AgmDirection.prototype.setMarker = /**
     * Custom Origin and Destination Icon
     * \@memberof AgmDirection
     * @param {?} map map
     * @param {?} marker marker
     * @param {?} markerOpts properties
     * @param {?} content marker's infowindow content
     * @return {?} new marker
     */
    function (map, marker, markerOpts, content) {
        var _this = this;
        if (typeof this.infoWindow === 'undefined') {
            this.infoWindow = new google.maps.InfoWindow({});
            this.sendInfoWindow.emit(this.infoWindow);
        }
        marker = new google.maps.Marker(markerOpts);
        marker.addListener('click', function () {
            /** @type {?} */
            var infowindoContent = typeof markerOpts.infoWindow === 'undefined' ? content : markerOpts.infoWindow;
            _this.infoWindow.setContent(infowindoContent);
            _this.infoWindow.open(map, marker);
        });
        return marker;
    };
    /**
     * This event is fired when remove markers
     */
    /**
     * This event is fired when remove markers
     * @return {?}
     */
    AgmDirection.prototype.removeMarkers = /**
     * This event is fired when remove markers
     * @return {?}
     */
    function () {
        if (typeof this.originMarker !== 'undefined') {
            this.originMarker.setMap(null);
        }
        if (typeof this.destinationMarker !== 'undefined') {
            this.destinationMarker.setMap(null);
        }
        this.waypointsMarker.forEach(function (w) {
            if (typeof w !== 'undefined') {
                w.setMap(null);
            }
        });
    };
    /**
     * This event is fired when remove directions
     */
    /**
     * This event is fired when remove directions
     * @return {?}
     */
    AgmDirection.prototype.removeDirections = /**
     * This event is fired when remove directions
     * @return {?}
     */
    function () {
        this.directionsDisplay.setPanel(null);
        this.directionsDisplay.setMap(null);
        this.directionsDisplay = undefined;
    };
    /**
     * This event is fired when destroy markers
     */
    /**
     * This event is fired when destroy markers
     * @return {?}
     */
    AgmDirection.prototype.destroyMarkers = /**
     * This event is fired when destroy markers
     * @return {?}
     */
    function () {
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
            this.waypointsMarker.forEach(function (w) {
                if (typeof w !== 'undefined') {
                    google.maps.event.clearListeners(w, 'click');
                }
            });
            this.removeMarkers();
        }
        catch (err) {
            console.error('Can not reset custom marker.', err);
        }
    };
    AgmDirection.decorators = [
        { type: Directive, args: [{
                    selector: 'agm-direction',
                },] }
    ];
    /** @nocollapse */
    AgmDirection.ctorParameters = function () { return [
        { type: GoogleMapsAPIWrapper }
    ]; };
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
    return AgmDirection;
}());
export { AgmDirection };
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