
import { Component, ViewChild, ElementRef } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import leaflet from 'leaflet';
import axios from 'axios';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  @ViewChild('map') mapContainer: ElementRef;
  map: any;
  inputLocation = '';
  inputDestination = '';
  constructor(public navCtrl: NavController, public alertCtrl: AlertController) {

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
  }

  // post to db and create alert box if successful, else log error
  postToDB() {
    axios.post('http://localhost:3000/routing', {
      location: this.inputLocation,
      destination: this.inputDestination,
    }).then(resp => {
      console.log(resp.data);
      let alert = this.alertCtrl.create({
        title: "Routing...",
        subTitle: this.inputLocation + " to " + this.inputDestination,
        buttons: ['OK']
      });
      alert.present();
    }).catch(error => {
      console.log(error);
    });
  }


}
