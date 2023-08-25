import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../_services/auth.service';
import { DataService } from '../../_services/data.service';
import * as CryptoJS from 'crypto-js';
import { HttpHeaders } from '@angular/common/http';
import { NgxSpinnerService } from "ngx-spinner";

@Component({
  selector: 'app-text-decrypt',
  templateUrl: './text-decrypt.component.html',
  styleUrls: ['./text-decrypt.component.css']
})
export class TextDecryptComponent implements OnInit {
  dcrptObj: any;
  dcrptMsgForm: FormGroup;
  showMsg = false;
  secreatMsg: any;
  ipAddress: any;
  reciverIp: any;
  reciversDetails: Object;
  http: any;
  SecuredKey: string = "";
  constructor(private authService: AuthService, private spinner: NgxSpinnerService
    , private dataService: DataService, private formBuilder: FormBuilder) { }
  ngOnInit() {
    this.getIPAddress();
    this.dcrptObj = this.authService.getMsg();
    this.dcrptMsgForm = this.formBuilder.group({
      text: ['', Validators.required],
      key: ['', Validators.required],
    });

  }
  onSubmit() {
    this.spinner.show();
    if (this.dcrptObj == "" || this.dcrptObj == null || this.dcrptObj == undefined) {
      alert("Something went Wrong please try again");
      this.spinner.hide();
      return;
    }
    var controls = this.dcrptMsgForm.controls;

    var Obj = {
      id: this.dcrptObj.id,
      text: this.dcrptObj.encryptedData,
      key: controls.key.value,
    };

    this.dataService.dcrptTxt(Obj).subscribe(res => {
      let data = res.body;
      if (data['txt'] == "" || data['txt'] == null || data['txt'] == undefined) {
        alert("Please check the key you enterd !");
        this.spinner.hide();
        return;
      } else {
        this.showMsg = true;
        this.secreatMsg = data['txt'];
        this.reciverIp = data['ip'];

        let dcData = this.decryptData(data, this.dcrptMsgForm.controls.key.value);
        if (dcData) {
          this.dcrptMsgForm.controls['text'].setValue(dcData);4
          this.spinner.hide();

        }

      }
    })

  }

  getIPAddress() {
  }

  decryptData(data, key) {
    try {
      const bytes = CryptoJS.AES.decrypt(data['txt'], key);
      if (bytes.toString()) {
        return JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
      }
      return data;
    } catch (e) {
      console.log(e);
    }
  }

  getKey() {
    var length = 16,
        charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
        retVal = "";
    for (var i = 0, n = charset.length; i < length; ++i) {
        retVal += charset.charAt(Math.floor(Math.random() * n));
    }
    this.SecuredKey = retVal;
}
}

