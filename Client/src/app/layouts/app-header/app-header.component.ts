import { Component, AfterViewInit } from '@angular/core';
import { DataService } from '../../_services/data.service';
import { AuthService } from '../../_services/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: '[app-header]',
  templateUrl: './app-header.component.html',
})
export class AppHeader implements AfterViewInit {
  userName : String = "";
  messages: any;
  userNumId: any;
  msgForDcrpt: any;
  showAlertIcon: boolean = false;

  constructor( private router: Router,private dataService: DataService,private authService: AuthService,) { }

  ngOnInit() {
    this.userNumId = this.authService.getUserNumId();
    this.dataService.getMsgs(this.userNumId).subscribe(data => {
      this.messages = data;
      this.msgList(data);
    });
    this.userName=this.authService.getUserNameToken(); 
  }
  msgList(data) {
    if(data){
      data.forEach(element => {
        if(element.reciverLocation == "" ||element.reciverLocation == null || element.reciverLocation == undefined){
          this.showAlertIcon = true;
          return;
        }
      });
    }
  }
  ngAfterViewInit()  {
  }
  
  logout(){
    this.authService.logout();
  }


}
