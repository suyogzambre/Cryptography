import { Component, OnInit } from '@angular/core';
import { alertcenter } from 'googleapis/build/src/apis/alertcenter';
import { AuthService } from '../../_services/../_services/auth.service';
import * as moment from 'moment';
import { NgxSpinnerService } from 'ngx-spinner';
// import { AuthService } from 'src/app/_services/auth.service';


@Component({
  selector: 'app-dashb',
  templateUrl: './dashb.component.html',
  styleUrls: ['./dashb.component.css']
})
export class DashbComponent implements OnInit {
  mail = "securedworld3@gmail.com"


  msgObj = {
    'id' : "",
    'time' :'',
    "email" :'',
    "mobileNo" :'',
    "message" :'',
    'issue' :'',
  }
  constructor(private auth : AuthService,private spinner: NgxSpinnerService) { }

  ngOnInit() {


  }


  onnMsgSend(){
    this.spinner.show();
    let currentDateTime = moment().format("MM/DD/YYYY hh:mm:ss");
    
    this.msgObj.time   = currentDateTime;
    this.auth.getInqury(this.msgObj);
    this.spinner.hide();

  

}
}
