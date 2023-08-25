import { Component, OnInit, AfterViewInit } from '@angular/core';
import * as $ from "jquery";

declare  var $:  any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']

})
export class HomeComponent implements OnInit {
  constructor() { }
  ngOnInit() {
	$('body').toggleClass('sidebar-mini');

  
    }

  }
    $('.js-sidebar-toggler').click(function() {
      $('body').toggleClass('sidebar-mini');
  });
