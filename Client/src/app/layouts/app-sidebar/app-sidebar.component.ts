import { Component } from '@angular/core';
import { AuthService } from '../../_services/auth.service';

@Component({
  selector: '[app-sidebar]',
  templateUrl: './app-sidebar.component.html',
  styleUrls: ['./app-sidebar.component.css']
})
export class AppSidebar {
  userRole: any;
  userPerm: any;

  constructor(private authService: AuthService) { }

  ngOnInit() {
  
  }
}
