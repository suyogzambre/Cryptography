import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { retry } from 'rxjs-compat/operator/retry';
import { AuthService } from '../../_services/auth.service';
import { DataService } from '../../_services/data.service';
import * as CryptoJS from 'crypto-js';
import { NgxSpinnerService } from 'ngx-spinner';
import { HttpClient } from '@angular/common/http';
import { config } from '../../_services/config';
import {  HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
@Component({
  selector: 'app-reset-pw',
  templateUrl: './reset-pw.component.html',
  styleUrls: ['./reset-pw.component.css']
})
export class ResetPwComponent implements OnInit {
  userDetils: any;
  showUserIdBox = true;
  showOtpBox = false;
  // showPasswordBox = false;

  constructor(private dataService: DataService, private formBuilder: FormBuilder,
    private auth: AuthService, private spinner: NgxSpinnerService,private router: Router,
    private http: HttpClient,) { }


  objForm: FormGroup;
  ngOnInit() {
    this.objForm = this.formBuilder.group({
      otp: [''],
      userId: [''],
      password: [''],
      passwordr: [''],
    });
  }


  onSubmituUserId() {
    this.spinner.show();
    let userId = this.objForm.controls.userId.value;
    if (userId == "" || userId == undefined || userId == null) {
      alert("Please Enter the userId");
      this.spinner.hide();
      return;
    } else {
      this.userDetils = this.auth.getUser(userId).subscribe(res =>{
        this.userDetils = res;
        console.log(this.userDetils);
        this.sendOtp()
      });
    }

  }

sendOtp(){
  if (!this.userDetils) {
    alert("Sorry we couldnt find your userid !");
    this.spinner.hide();
    return;
  } else {
    this.auth.sendOTP(this.userDetils.userNumId).subscribe(res =>{
      if(!res){
        this.showOtpBox = true;
        this.showUserIdBox = false;
        this.spinner.hide();
        return;
      }
    });
  }
  
}

onSubmitOtp() {
  let pw1 = this.objForm.controls.password.value;
  let pw2 = this.objForm.controls.passwordr.value;
 
  if (pw1 === pw2) {
    this.userDetils['password'] = pw1;
    this.userDetils['otp'] = this.objForm.controls.otp.value;

    if(this.userDetils['password'].length < 8 ){
      alert("Please use a strong password!")
      return;
    }
    this.auth.resetPw(this.userDetils).subscribe(res =>{
      if(res){
            alert("Succefully reseted your password, please Login with your new creads!");
            this.router.navigate(['/login']);
      }else{
        alert("please check your otp");
      }
    });
  }
}
}
