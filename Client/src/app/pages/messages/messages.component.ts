import { Component, OnInit } from '@angular/core';
import { DataService } from '../../_services/data.service';
import { AuthService } from '../../_services/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  messages: any;
  userNumId: any;
  msgForDcrpt: any;
  usrList: any;

  constructor( private router: Router,private dataService: DataService,private authService: AuthService,) { }

  ngOnInit() {
    this.userNumId = this.authService.getUserNumId();
    this.dataService.getMsgs(this.userNumId).subscribe(data => {
      this.messages = data;
    });
    this.dataService.fetchAllUser().subscribe(res=>{
      this.usrList = res;
      this.usrList.forEach(element => {
        this.messages.forEach(element2 => {
          if(element.userNumId ==  element2.senderName){
            element2['userCName'] = element.completeName;
          }
          
        });
        
      });
    })
  }

  deleteMsg(id){
    if(confirm("Are you sure you want to delete this message to , you are not able to read it after delete .")) {
    this.dataService.deleteMsg(id).subscribe(res => {
      if(!res){
        alert("Deleted!");
        this.ngOnInit();
      }
    })
  }

  }
  sendMsg(msg){
    this.authService.setDcrptMsg(msg);
    if(msg.type == "text"){
      this.router.navigate(['/txtDecrypt']);
    }else if(msg.type =="img"){
      this.authService.setDcrptMsgForImg();
      this.router.navigate(['/imgDcrpt']); 
    }else if(msg.type =="PDF"){
      this.authService.setDcrptMsgForImg();
      this.router.navigate(['/docDcrpt']); 
    }
  }
}
