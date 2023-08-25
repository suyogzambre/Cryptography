import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../_services/auth.service';
import { DataService } from '../../_services/data.service';
import * as CryptoJS from 'crypto-js';
import { HttpHeaders } from '@angular/common/http';
// import { ClipboardService } from 'ngx-clipboard';

@Component({
  selector: 'app-gen-key',
  templateUrl: './gen-key.component.html',
  styleUrls: ['./gen-key.component.css']
})
export class GenKeyComponent implements OnInit {
 
  SecuredKey = "";
  showAlrt : boolean = false;
  constructor(private authService: AuthService,
     private dataService: DataService) { }
  ngOnInit() {}

  getKey() {
    var length = 16,
        charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
        retVal = "";
    for (var i = 0, n = charset.length; i < length; ++i) {
        retVal += charset.charAt(Math.floor(Math.random() * n));
    }
    this.SecuredKey = retVal;
}
  copyText() {
    // this._clipboardService.copy(this.SecuredKey)
    navigator.clipboard.writeText(this.SecuredKey);
    alert("Text Coppied");
}

}

