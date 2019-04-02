
import { Component, ViewChild, ElementRef } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import leaflet from 'leaflet';
import polyUtil  from 'polyline-encoded'
import { SignupPage } from '../signup/signup';
@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  @ViewChild('map') mapContainer: ElementRef;
  map: any;
  signupPage = SignupPage;
  inputLocation = ''
  inputDestination = ''
  currentLatlng: any
  marker: leaflet.marker
  startMarker: leaflet.marker
  destMarker: leaflet.marker
  blockMarker: leaflet.marker
  simMarker: leaflet.marker
  sim: any
  simGroup: leaflet.featureGroup
  constructor(public navCtrl: NavController, public alertCtrl: AlertController ) {}

  ionViewDidEnter() {
    this.loadmap();
  }

  simulateBlock(){
    //let simGroup = this.simGroup;

    var url = "http://localhost:8080/getSimulation/";
    let data = '';
    const http = require('http')
    http.get(url, (resp) => {
      // save the JSON in data
      resp.on('data', (chunk) => {
        data += chunk;
      }).on('end',()=>{
        this.sim = JSON.parse(data);
        this.sim = this.sim['data'];
        if (this.simGroup){
          this.map.removeLayer(this.simGroup);
        }
        let simGroup = leaflet.featureGroup();
        var i = 0 ;
        while( i<10 ) {
          this.simMarker= leaflet.marker([this.sim[i]['lat'],this.sim[i]['lng']]);
          simGroup.addLayer(this.simMarker);
          i+=1;
        }
        this.map.addLayer(simGroup);
        this.simGroup = simGroup;
      })
      }).on("error", (err) => {
      console.log("Error: " + err.message);
    });
  }

  setBlock() {
    var blockMarker = this.blockMarker
    var map = this.map
    var getRouteWithBlock = this.getRouteWithBlock
    var inputLocation = this.inputLocation
    var inputDestination = this.inputDestination
    var startMarker = this.startMarker
    var destMarker = this.destMarker

    var onClickSetBlock = this.onClickSetBlock
    var getRouteWithBlock = this.getRouteWithBlock
    var drawPolyline = this.drawPolyline

    map.on('click', function(e) {

    var blockLatLng = e.latlng;

    if (blockMarker) {
      map.removeLayer(blockMarker);
    }

    let markerGroup = leaflet.featureGroup()
    console.log("Adding:::"+blockLatLng.lat)
    blockMarker = leaflet.marker([blockLatLng.lat, blockLatLng.lng]);
    markerGroup.addLayer(blockMarker);
    console.log(map)
    map.addLayer(markerGroup);
    console.log("You clicked the map at latitude: " + blockLatLng.lat + " and longitude: " + blockLatLng.lng);

    console.log("getting block route: "+inputLocation)
    //getRouteWithBlock(inputLocation, inputDestination, blockLatLng.Lat, blockLatLng.Lng)
    onClickSetBlock(map, getRouteWithBlock, blockLatLng,inputLocation, inputDestination, drawPolyline, startMarker, destMarker)
    });
  }

  onClickSetBlock(map, getRouteWithBlock, blockLatLng, inputLocation, inputDestination, drawPolyline, startMarker, destMarker){

    console.log(map)
    console.log("You clicked the map at latitude: " + blockLatLng.lat + " and longitude: " + blockLatLng.lng);

    console.log("getting block route: "+inputLocation)
    getRouteWithBlock(inputLocation, inputDestination, blockLatLng.lat, blockLatLng.lng, drawPolyline, map, startMarker, destMarker)
  }

   getLocation(){
     this.map.locate({
       setView: true
     }).once('locationfound', (e) => {
      this.currentLatlng = e.latlng

      //remove the marker before adding a new one
      let markerGroup = leaflet.featureGroup();
      if (this.marker) {
        this.map.removeLayer(this.marker);
      }
      this.marker = leaflet.marker([e.latitude, e.longitude])
      markerGroup.addLayer(this.marker);
      this.map.addLayer(markerGroup);

      }).on('locationerror', (err) => {
          console.log(err.message);
      })
  }

    showLocation(){
      this.getLocation()

      let alertOfLoc = this.alertCtrl.create({
        title:"YOUR CURRENT LOCATION",
        subTitle: "Latitude:" + this.currentLatlng.lat+" Longitude: " +this.currentLatlng.lng,
        buttons:['GOT IT']
      });
    alertOfLoc.present();
  }



  loadmap() {
    if(this.map){
      this.map.remove();
    }
    this.map = leaflet.map("map").fitWorld();
    leaflet.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attributions: 'www.tphangout.com',
      maxZoom: 18,

    }).addTo(this.map)
    this.getLocation()

    var inputDestination = this.inputDestination;
    this.map.on('click', function(e){
      var coord = e.latlng;
      var lat = coord.lat;
      var lng = coord.lng;
      console.log("You clicked the map at latitude: " + lat + " and longitude: " + lng);
      });
  }

  getDirections(){
    console.log("GetDirections: "+this.inputLocation)
    var i;
    for (i in this.map._layers) {
      this.map.removeLayer(this.map._layers[i])
    }
    leaflet.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attributions: 'www.tphangout.com',
      maxZoom: 18
    }).addTo(this.map);
    //this.getLocation()

    this.getRoute(this.inputLocation, this.inputDestination)
  }

  //ToDo change method to take in starting lat/long and route to destination lat/long
  getRoute(start, destination){
    console.log("routing:"+start)
    if(start == ""){
      alert("Starting location cannot be empty!")
      return;
    }

    if(destination == ""){
      alert("Destination location cannot be empty!")
      return;
    }
    //ToDo: change url to deployed server version
    var url = "http://localhost:8081/navigation/start/"+start+"/destination/"+destination+"/walking/john"
    const http = require('http')
    http.get(url, (resp) => {
      let data = '';

      // A chunk of data has been recieved.
      resp.on('data', (chunk) => {
        data += chunk;
      });

      // The whole response has been received. Print out the result.
      resp.on('end', () => {
        this.drawPolyline(data, this.map, this.startMarker, this.destMarker)
      });

    }).on("error", (err) => {
      console.log("Error: " + err.message);
    });
  }

  getRouteWithBlock(start, destination, blockLat, blockLng, drawPolyline, map, startMarker, destMarker){
    var drawPolyline = drawPolyline

    console.log("routing:"+start)
    if(start == ""){
      alert("Starting location cannot be empty!")
      return;
    }

    if(destination == ""){
      alert("Destination location cannot be empty!")
      return;
    }

    if(blockLat == ""){
      alert("Please choose a block location on the map")
    }

    if(blockLng == ""){
      alert("Please choose a block location on the map")
    }

    //ToDo: change url to deployed server version
    var url = "http://localhost:8081/navigation/start/"+start+"/destination/"+destination+"/walking/avoid/"+blockLat+"/"+blockLng+"/Nicky"
    const http = require('http')
    http.get(url, (resp) => {
      let data = '';

      // A chunk of data has been recieved.
      resp.on('data', (chunk) => {
        data += chunk;
      });

      // The whole response has been received. Print out the result.
      resp.on('end', () => {
        drawPolyline(data, map, startMarker, destMarker)
      });

    }).on("error", (err) => {
      console.log("Error: " + err.message);
    });
  }


/*
  Walking - Green
  Driving - Red
  Cycling - Purple
  Transit - Blue

*/

getWeather(startLat, startLng, iconCode){

  //corresponding icon shows up
}

  drawPolyline(data, map, startMarker, destMarker){
    function chooseColor(step){
      var mode = step['travel_mode']
      if(mode == "DRIVING")
        return "#ff0000"
      else if(mode == "WALKING")
        return "#00ff2e"
      else if(mode == "BICYCLING")
        return "#8700ff"
      else
        return "#0065ff"
    }
    var jsonData = JSON.parse(data)
    if (startMarker) {
      map.removeLayer(startMarker);
    }
    if (destMarker) {
      map.removeLayer(destMarker);
    }

    let markerGroup = leaflet.featureGroup()

      jsonData = jsonData['routes']

      jsonData.forEach(function(route) {
            var legs = route['legs']

            legs.forEach(function(leg){

              var steps = leg['steps']
              var color = "#"+((1<<24)*Math.random()|0).toString(16)

              var startMarkerLatLng = [steps[0]['start_location']["lat"], steps[0]['start_location']["lng"]]
              var destMarkerLatLng = [steps[steps.length-1]['end_location']['lat'],steps[steps.length-1]['end_location']['lng']]

              startMarker = leaflet.marker([startMarkerLatLng[0], startMarkerLatLng[1]])
              destMarker = leaflet.marker([destMarkerLatLng[0], destMarkerLatLng[1]])
              markerGroup.addLayer(startMarker);
              markerGroup.addLayer(destMarker);
              map.addLayer(markerGroup);

              steps.forEach(function(step){

                function addTransitMarker(lat, lng, transit_details, map){
                   markerGroup = leaflet.featureGroup()
                  console.log("Adding:::"+lat)


                  var redIcon = new leaflet.Icon({
                      iconUrl: 'assets/imgs/marker-icon-2x-red.png',
                      shadowUrl: 'assets/imgs/marker-shadow.png',
                      iconSize: [25, 41],
                      iconAnchor: [12, 41],
                      popupAnchor: [1, -34],
                      shadowSize: [41, 41]
                    });

                  var transitMarker = leaflet.marker([lat, lng], {icon: redIcon}).addTo(map).on('click', function(e){
                        alert("Transport Mode: "+transit_details['line']['vehicle']['name']+"\n"
                      +"Stop Name: "+transit_details['departure_stop']['name']+"\n"
                      +"Departure Time: "+transit_details['departure_time']['text']);
                  });
                }


                var mode = step['travel_mode'];
                if(mode == "TRANSIT"){
                  addTransitMarker(step['start_location']['lat'], step['start_location']['lng'], step['transit_details'], map)
                }

                var polyline = step['polyline']['points']
                var coordinates = polyUtil.decode(polyline);

                var polyline = leaflet.polyline(coordinates, {
                  color: chooseColor(step),
                  weight: 10,
                  opacity: .7,
                  dashArray: '0,0',
                  lineJoin: 'round'
                }).addTo(map)
              })
            })
      })
  }

}
