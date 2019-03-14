
import { Component, ViewChild, ElementRef } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import leaflet from 'leaflet';
import polyUtil  from 'polyline-encoded'

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  @ViewChild('map') mapContainer: ElementRef;
  map: any;
  inputStartLocation = ''
  inputDestination = ''
  currentLatlng: any
  marker: leaflet.marker
  startMarker: leaflet.marker
  destMarker: leaflet.marker


  constructor(public navCtrl: NavController, public alertCtrl: AlertController ) {}

  ionViewDidEnter() {
    this.loadmap();
  }

   getLocation(){
     this.map.locate({
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
    this.map = leaflet.map("map").fitWorld();
    leaflet.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attributions: 'www.tphangout.com',
      maxZoom: 18
    }).addTo(this.map);
    this.getLocation()

    this.map.on('click', this.onMapClickCallBack);
  }

  setInputStartLocation(input){
      this.inputStartLocation = input
      console.log("SETting --->"+this.inputStartLocation)
  }

  setInputDestination(input){
    console.log("setDest1.."+input + " ..."+this.inputDestination)
      this.inputDestination = input
      console.log("setDest2.."+input)
  }

  onMapClickCallBack(event) {
    var coord = event.latlng;
    var lat = coord.lat;
    var lng = coord.lng;
    console.log("You clicked the map at latitude: " + lat + " and longitude: " + lng);

    if(this.inputStartLocation == null){
      this.inputStartLocation = lat+","+lng
      console.log("SRC---"+this.inputStartLocation  )
    }
    else if(this.inputDestination == null){
      this.inputDestination = lat+","+lng
      console.log("DEST---"+this.inputDestination)
    }

        console.log(this.inputStartLocation)
        console.log(this.inputDestination)
  }

  getDirections(){
    console.log("GetDirections: "+this.inputStartLocation)
    this.getPolyLine(this.inputStartLocation, this.inputDestination)
  }

  //ToDo change method to take in starting lat/long and route to destination lat/long
  getPolyLine(start, destination){
    //ToDo: change url to deployed server version
    var url = "http://localhost:3000/routes"

    const http = require('http')
    http.get(url, (resp) => {
      let data = '';

      // A chunk of data has been recieved.
      resp.on('data', (chunk) => {
        data += chunk;
      });

      // The whole response has been received. Print out the result.
      resp.on('end', () => {
        var jsonData = JSON.parse(data)

        var map = this.map
        var startMarker = this.startMarker
        var destMarker = this.destMarker
        if (startMarker) {
          map.removeLayer(startMarker);
        }
        if (destMarker) {
          map.removeLayer(destMarker);
        }

        jsonData.forEach(function(route) {
              var legs = route['legs']

              legs.forEach(function(leg){
                var steps = leg['steps']

                var startMarkerLatLng = [steps[0]['start_location']["lat"], steps[0]['start_location']["lng"]]
                var destMarkerLatLng = [steps[steps.length-1]['end_location']['lat'],steps[steps.length-1]['end_location']['lng']]

                let markerGroup = leaflet.featureGroup()

                startMarker = leaflet.marker([startMarkerLatLng[0], startMarkerLatLng[1]])
                destMarker = leaflet.marker([destMarkerLatLng[0], destMarkerLatLng[1]])
                markerGroup.addLayer(startMarker);
                markerGroup.addLayer(destMarker);
                map.addLayer(markerGroup);

                steps.forEach(function(step){
                  var polyline = step['polyline']['points']
                  var coordinates = polyUtil.decode(polyline);

                  var polyline = leaflet.polyline(coordinates, {
                    color: "#"+((1<<24)*Math.random()|0).toString(16),
                    weight: 10,
                    opacity: .7,
                    dashArray: '0,0',
                    lineJoin: 'round'
                  }).addTo(map)
                })
              })
        })
      });

    }).on("error", (err) => {
      console.log("Error: " + err.message);
    });

  }
}
