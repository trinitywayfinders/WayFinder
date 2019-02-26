import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the SignupPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-signup',
  templateUrl: 'signup.html',
})
export class SignupPage {
  usrname='';
  passwd='';
  const ax = require('axios');
  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad SignupPage');
  }
  // // POST
  // axios.post('http://localhost:3000/routing', {
  //   id: 8,
  // first_name: 'Fred',
  // last_name: 'Blair',
  // email: 'freddyb34@gmail.com'
  // }).then(resp => {
  //   console.log(resp.data);
  // }).catch(error => {
  // console.log(error);
  // });

//GET
signup(){
  console.log(this.usrname);
  console.log(this.passwd);
  this.ax.get('http://localhost:3001')
    .then(resp => {
        console.log(resp['data']);
    })
    .catch(error => {
        console.log(error);
    });

  }

}
