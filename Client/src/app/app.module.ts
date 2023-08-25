import { BrowserModule } from '@angular/platform-browser';
import { CommonModule, DatePipe } from '@angular/common'; 
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AuthModule } from './auth.module';
import { NgxSpinnerModule } from "ngx-spinner";
import { PdfViewerModule } from 'ng2-pdf-viewer';
import {DataTableModule} from "angular-6-datatable";
import { DeviceDetectorModule } from 'ngx-device-detector';
// import { ClipboardModule } from 'ngx-clipboard';
import { AppComponent } from './/app.component';
import { AppRoutingModule } from './/app-routing.module';
import { LayoutModule } from './/layouts/layout.module';
import { ScriptLoaderService } from './_services/script-loader.service';
import { DataService } from './_services/data.service';
import { TextDecryptComponent } from './pages/text-decrypt/text-decrypt.component';
import { TextEncComponent } from './pages/text-enc/text-enc.component';
import { MessagesComponent } from './pages/messages/messages.component';
import { UserDetilsComponent } from './pages/user-detils/user-detils.component';
import { ImgEncComponent } from './pages/img-enc/img-enc.component';
import { HelperService } from './_services/helper.service';
import { ImgDecryptComponent } from './pages/img-decrypt/img-decrypt.component';
import { RegisterComponent } from './pages/register/register.component';
import { DocDecryptComponent } from './pages/doc-decrypt/doc-decrypt.component';
import { DocEncComponent } from './pages/doc-enc/doc-enc.component';
import { DashbComponent } from './pages/dashb/dashb.component';
import { SupportComponent } from './pages/support/support.component';
import { ResetPwComponent } from './pages/reset-pw/reset-pw.component'
import { ErrorPageComponent } from './pages/error-page/error-page.component';
import { GenKeyComponent } from './pages/gen-key/gen-key.component';
// import { TypingAnimationDirective } from 'angular-typing-animation'
@NgModule({
   declarations: [
    AppComponent,
    TextDecryptComponent,
    TextEncComponent,
    MessagesComponent,
    UserDetilsComponent,
    ImgEncComponent,
    ImgDecryptComponent,
    RegisterComponent,
    DocDecryptComponent,
    DocEncComponent,
    SupportComponent,
    DashbComponent,
    ResetPwComponent,
    ErrorPageComponent,
    GenKeyComponent

  ],
  imports: [
    AppRoutingModule,
    LayoutModule,
    HttpClientModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AuthModule,
    BrowserAnimationsModule,
    NgxSpinnerModule,
    PdfViewerModule,
    DataTableModule,
    DeviceDetectorModule,
  ],
  providers: [ScriptLoaderService, DataService, DatePipe,HelperService],
  bootstrap: [AppComponent]
})
export class AppModule { }
