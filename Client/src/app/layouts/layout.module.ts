import {NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {RouterModule} from '@angular/router';

import { LayoutComponent } from './/layout.component';
import { AppHeader } from './/app-header/app-header.component';
import { AppSidebar } from './/app-sidebar/app-sidebar.component';

@NgModule({
	declarations: [
	  LayoutComponent,
	  AppHeader,
	  AppSidebar,
	
	 
	],
	exports: [
	  LayoutComponent,
	  AppHeader,
	  AppSidebar,

	  
	],
	imports: [
		BrowserModule,
		RouterModule,
	]
})
export class LayoutModule {
}