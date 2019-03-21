import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Storage } from '@ionic/storage';
/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
    username: string;
    password: string;
    form: any;
    httpOptions: any;
    storage: Storage;

    constructor(public navCtrl: NavController, public navParams: NavParams, public http: HttpClient) {
    }

    ionViewDidLoad() {
        console.log('ionViewDidLoad LoginPage');
    }

    login() {
        this.form = new FormData();
        this.form.set('grant_type', 'password');
        this.form.set('username', this.username);
        this.form.set('password', this.password);
        this.form.set('client_id', 'web-app');

        // axios({
        //     method: 'POST',
        //     url: 'http://localhost:8080/oauth/token',
        //     headers: {
        //         'Content-Type': 'multipart/form-data;charset=UTF-8',
        //         'Accept': 'application/json;charset=UTF-8',
        //         // 'Authorization': 'Basic d2ViLWFwcDoxMjM0NTY=',
        //     },
        //     auth: {
        //         username: 'web-app',
        //         password: '123456'
        //     },
        //     data: this.form
        // }).then(function (response) {
        //   console.log(response);
        // }).catch(function (error) {
        //   console.log(error);
        // }).then(function () {
        //   // always executed
        // });

        this.httpOptions = {
            headers: new HttpHeaders({
                // 'Content-Type': 'multipart/form-data;charset=UTF-8',
                'Accept': 'application/json;charset=UTF-8',
                'Authorization': 'Basic d2ViLWFwcDoxMjM0NTY=',
            })
        }
        console.log(this.form)
        this.http.post('http://localhost:8080/oauth/token', this.form, this.httpOptions).subscribe((response) => {
            console.log(response);
            console.log(typeof response);

            console.log(response.access_token);
            console.log(response.refresh_token);

            // this.storage.set('refresh_token', response.refresh_token);
            // this.storage.set('access_token', response.access_token);

            this.navCtrl.popToRoot();
        });

    }
}
