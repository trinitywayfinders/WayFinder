
import { Component, ViewChild, ElementRef } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import leaflet from 'leaflet';
import polyUtil  from 'polyline-encoded'

import { SignupPage } from '../signup/signup';
import { LoginPage } from '../login/login';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  @ViewChild('map') mapContainer: ElementRef;
  map: any;
  inputLocation = '';
  inputDestination = '';
  currentLatlng: any;
  marker: leaflet.marker;
  signupPage = SignupPage;
  loginPage = LoginPage;


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
    console.log("Loading map...")
    this.map = leaflet.map("map").fitWorld();
    leaflet.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attributions: 'www.tphangout.com',
      maxZoom: 18
    }).addTo(this.map);
    this.getLocation()
    this.getPolyLine()
  }


  getPolyLine(){
    console.log("getPolyLine")
    var url = "http://localhost:3000/routes"

    const http = require('http')

    http.get(url, (resp) => {
      let data = '';

      // A chunk of data has been recieved.
      resp.on('data', (chunk) => {
        data += chunk;
        console.log(chunk)
      });

      // The whole response has been received. Print out the result.
      resp.on('end', () => {
        var jsonData = JSON.parse(data)

        var map = this.map
        jsonData.forEach(function(route) {
              var legs = route['legs']

              legs.forEach(function(leg){
                var steps = leg['steps']

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
              /*var points = overview_polyline['points']

              var coordinates = polyUtil.decode(points);

              var polyline = leaflet.polyline(coordinates, {
                color: 'red',
                weight: 10,
                opacity: .7,
                dashArray: '0,0',
                lineJoin: 'round'
              }).addTo(map)
              */
        })
      });

    }).on("error", (err) => {
      console.log("Error: " + err.message);
    });

  }

}
