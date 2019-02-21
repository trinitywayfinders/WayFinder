
import { Component, ViewChild, ElementRef } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import leaflet from 'leaflet';

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
  marker: leaflet.market

  constructor(public navCtrl: NavController, public alertCtrl: AlertController) {
  }

  ionViewDidEnter() {
    this.loadmap();
  }

   getLocation(){
     this.map.locate({
     }).once('locationfound', (e) => {
      console.log("ONCE")
      this.currentLatlng = e.latlng

      let markerGroup = leaflet.featureGroup();
      if (this.marker) { // check
        this.map.removeLayer(this.marker); // remove
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
  }
}
