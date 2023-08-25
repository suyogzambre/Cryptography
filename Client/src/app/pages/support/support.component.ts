import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import * as moment from 'moment';
import { NgxSpinnerService } from 'ngx-spinner';
@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent implements OnInit {


  msgObj = {
    'id' : "",
    'time' :'',
    "email" :'',
    "mobileNo" : 0,
    "message" :'',
    'issue' :'',
  }
  userNumId: string;
  constructor(private auth : AuthService,private spinner: NgxSpinnerService) { }

  ngOnInit() {
    this.userNumId = this.auth.getUserNumId()
  }


onSubmit(){
  this.spinner.show()
  this.msgObj.time = moment().format("MM/DD/YYYY hh:mm:ss");
  this.msgObj.id = this.userNumId;
  this.auth.getMsgInqury(this.msgObj).subscribe(res =>{
    alert("THank you for reachinng toward us , our tech team will reach to you sonn !")
    this.spinner.hide()
  });
}
  
}
