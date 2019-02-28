import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
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
  //alert for unmatch imput passwords
  showAlert(){
    let alert = this.alertCtrl.create({
      title:"Password does not match",
      subTitle: "Please enter the same password.",
      buttons:['GOT IT']
    });
  alert.present();
}
signup(){
  var axiosConfig = {
      headers: {
          'Content-Type': 'application/json',
          'accept' : '*/*',
      }
    };
    if (this.passwd1==this.passwd2){
      this.passwd = this.passwd1;
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
