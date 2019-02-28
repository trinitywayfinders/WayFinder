import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import axios from 'axios';
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
  passwd1='';
  passwd2='';
  email='';
  ax = require('axios');
  constructor(public navCtrl: NavController, public navParams: NavParams,  public alertCtrl: AlertController) {
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
//
// headers: {
//       'Content-Type': 'application/json',
//       'Authorization': 'Bearer ' + this.token
//     }
  showAlert(){
    let alertOfLoc = this.alertCtrl.create({
      title:"Password does not match",
      subTitle: "Please enter the same password.",
      buttons:['GOT IT']
    });
  alertOfLoc.present();
}

signup(){
  //console.log(this.usrname);
  //console.log(this.passwd);
  // this.ax.get('http://localhost:3001')
  //   .then(resp => {
  //       console.log(resp['data']);
  //   })
  //   .catch(error => {
  //       console.log(error);
  //   });
  var axiosConfig = {
      headers: {
          'Content-Type': 'application/json',
          'accept' : '*/*',
      }
    };
    if (this.passwd1==this.passwd2){
      passwd = passwd1;
      this.ax.post('http://localhost:8080/api/user/', {
      'username': this.usrname,
      'email': this.email,
      'password': this.passwd
    }, axiosConfig).then(resp => {
      console.log(resp);
    }).catch(error => {
    console.log(error);
    });
    }
    else{
      this.showAlert();
    }



  }

}
