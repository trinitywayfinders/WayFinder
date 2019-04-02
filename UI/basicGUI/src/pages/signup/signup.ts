import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import { LoginPage } from '../login/login';

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
    email='';
    concernCost=0;
    concernEmissionReduction=0;
    concernHealth=0;
    concernPollutionAvoidance=0;
    concernSpeed=0;
    ax = require('axios');
    constructor(public navCtrl: NavController, public navParams: NavParams,  public alertCtrl: AlertController) {
    }
    ionViewDidLoad() {
        console.log('ionViewDidLoad SignupPage');
    }
    //alert for unmatch input passwords
    showAlert(){
        let alert = this.alertCtrl.create({
            title:"Password does not match",
            subTitle: "Please enter the same password.",
            buttons:['GOT IT']
        });
        alert.present();
    }
    showDupAlert(){
        let alert = this.alertCtrl.create({
            title:"There are duplicate in preference",
            subTitle: "Please use each number only once.",
            buttons:['GOT IT']
        });
        alert.present();
    }
    showOverAlert(){
        let alert = this.alertCtrl.create({
            title:"There's rank over 5 in preference",
            subTitle: "Please choose from 1 to 5.",
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
        // if (this.passwd==this.passwd1){
        //   this.ax.post('http://35.246.76.168:8080/api/user/', {
        //   'username': this.usrname,
        //   'email': this.email,
        //   'password': this.passwd
        // }, axiosConfig).then(resp => {
        //   console.log(resp);
        // }).catch(error => {
        // console.log(error);
        // });
        // }
        // else{
        //   this.showAlert();
        // }


        if (this.passwd == this.passwd1 &&
            this.concernCost!=this.concernEmissionReduction &&
            this.concernEmissionReduction!=this.concernHealth &&
            this.concernHealth!=this.concernPollutionAvoidance &&
            this.concernPollutionAvoidance!=this.concernSpeed)
            {
                if(this.concernCost >5 ||
                    this.concernEmissionReduction >5 ||
                    this.concernHealth >5||
                    this.concernPollutionAvoidance >5 ||
                    this.concernSpeed>5)
                    {
                        this.showOverAlert();
                    }
                    else{
                        // this.ax.post('http://35.246.76.168:8080/api/getUserPrefs', {
                        //   'username': this.usrname,
                        //   'concernCost':this.concernCost,
                        //   'concernEmissionReduction':this.concernEmissionReduction,
                        //   'concernHealth':this.concernHealth,
                        //   'concernPollutionAvoidance':this.concernPollutionAvoidance,
                        //   'concernSpeed':this.concernSpeed
                        // }, axiosConfig).then(resp => {
                        //   console.log(resp);
                        // }).catch(error => {
                        //   console.log(error);
                        // });

                        console.log("About to POST")
                        this.ax.post('http://localhost:8080/api/user/', {
                            'username': this.usrname,
                            'password': this.passwd,
                            'email': this.email,
                            'userPrefs': {
                                'concernCost':this.concernCost,
                                'concernEmissionReduction':this.concernEmissionReduction,
                                'concernHealth':this.concernHealth,
                                'concernPollutionAvoidance':this.concernPollutionAvoidance,
                                'concernSpeed':this.concernSpeed
                            }
                        }, axiosConfig).then(resp => {
                            console.log(resp);
                            this.navCtrl.push(LoginPage);
                        }).catch(error => {
                            console.log(error);
                        });
                    }
                }
                else{
                    this.showDupAlert();
                }
            }
        }
