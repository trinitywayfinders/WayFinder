
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
  currentLatlng: any;
  inputLocation = '';
  inputDestination = '';

  constructor(public navCtrl: NavController, public alertCtrl: AlertController) {
  }


   getLocation():any{
      this.map.locate({
      }).on('locationfound', (e) => {

        //console.log('in locationFound '+currentLatlng.latlng)

        this.currentLatlng = e

        this.getCurrentLatLing()
      }).on('locationerror', (err) => {
          console.log(err.message);
      })
  }

  getCurrentLatLing(){
    let alertOfLoc = this.alertCtrl.create({
      title:"YOUR CURRENT LOCATION",
      subTitle: "Latitude:" + this.currentLatlng.latitude+" Longitude: " +this.currentLatlng.longitude,
      buttons:['GOT IT']
    });
    alertOfLoc.present();
  }

    ionViewDidEnter() {
      this.loadmap();
    }
  loadmap() {
    this.map = leaflet.map("map").fitWorld();
    leaflet.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attributions: 'www.tphangout.com',
      maxZoom: 18
    }).addTo(this.map);

    ///////Mark the current location
    this.map.locate({
      //setView: true,
      maxZoom: 10
    }).on('locationfound', (e) => {
      //this.currentLatlng = e;
      //alert(e.latlng);
      let markerGroup = leaflet.featureGroup();
      let marker: any = leaflet.marker([e.latitude, e.longitude])
      markerGroup.addLayer(marker);
      this.map.addLayer(markerGroup);
      }).on('locationerror', (err) => {
        alert(err.message);
    })


    //console.log(currentLatlng)
  //  console.log("WHAT")


    let markerGroup = leaflet.featureGroup();


    //let marker: any = leaflet.marker([currentLatlng[0], currentLatlng[1]]).on('click', () => {
    //    alert('Current Location!');
      //})
    //markerGroup.addLayer(marker);
    //this.map.addLayer(markerGroup);
  }
}
