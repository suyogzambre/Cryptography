import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { ComponentGuard } from './guards/component.guard';
import { LayoutComponent } from './/layouts/layout.component';
import { HomeComponent } from './pages/home/home.component';
import { ColorsComponent } from './pages/ui/colors/colors.component';
import { TypographyComponent } from './pages/ui/typography/typography.component';
import { PanelsComponent } from './pages/ui/panels/panels.component';
import { TabsComponent } from './pages/ui/tabs/tabs.component';
import { AlertsComponent } from './pages/ui/alerts/alerts.component';
import { CardsComponent } from './pages/ui/cards/cards.component';
import { BadgesProgressComponent } from './pages/ui/badges-progress/badges-progress.component';
import { ListComponent } from './pages/ui/list/list.component';
import { IconsComponent } from './pages/ui/icons/icons.component';
import { ButtonsComponent } from './pages/ui/buttons/buttons.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { TextEncComponent } from './pages/text-enc/text-enc.component';
import { TextDecryptComponent } from './pages/text-decrypt/text-decrypt.component';
import { MessagesComponent } from './pages/messages/messages.component';
import { UserDetilsComponent } from './pages/user-detils/user-detils.component';
import { ImgEncComponent } from './pages/img-enc/img-enc.component';
import { ImgDecryptComponent } from './pages/img-decrypt/img-decrypt.component';
import { DocEncComponent } from './pages/doc-enc/doc-enc.component';
import { DocDecryptComponent } from './pages/doc-decrypt/doc-decrypt.component';
import { DashbComponent } from './pages/dashb/dashb.component';
import { SupportComponent } from './pages/support/support.component';
import { ResetPwComponent } from './pages/reset-pw/reset-pw.component';
import { ErrorPageComponent } from './pages/error-page/error-page.component';
import { GenKeyComponent } from './pages/gen-key/gen-key.component';
//import { FormsModule } from '@angular/forms';
//import { ReactiveFormsModule } from '@angular/forms';

const routes: Routes = [
    {path: '', redirectTo: 'dashb', pathMatch: 'full'},
    {path:'dashb',component:DashbComponent,canActivate:[AuthGuard]},
    {path: 'login', component: LoginComponent, canActivate: [AuthGuard]},
    {path: 'register', component: RegisterComponent, canActivate: [AuthGuard]},
    {
        path :"resetPw",
        component:ResetPwComponent,
        canActivate:[AuthGuard]
    },
    {
        "path": "",
        "component": LayoutComponent,
        "children": [
            {
                path: "index",
                component: HomeComponent,
                canActivate: [ComponentGuard]
            },
            {
                path: "ui/colors",
                component: ColorsComponent
            },
            {
                path: "ui/typography",
                component: TypographyComponent
            },
            {
                path: "ui/panels",
                component: PanelsComponent
            },
            {
                path: "ui/buttons",
                component: ButtonsComponent
            },
            {
                path: "ui/tabs",
                component: TabsComponent
            },
            {
                path: "ui/alerts",
                component: AlertsComponent
            },
            {
                path: "ui/badges_progress",
                component: BadgesProgressComponent
            },
            {
                path: "ui/lists",
                component: ListComponent
            },
            {
                path: "ui/cards",
                component: CardsComponent
            },
            {
                path: "ui/icons",
                component: IconsComponent,
                canActivate: [ComponentGuard]
            },
            {
                path: "textEnc",
                component: TextEncComponent,
                canActivate: [ComponentGuard]
            },
            {
                path: "txtDecrypt",
                component: TextDecryptComponent,
                canActivate: [ComponentGuard]
            },
            {
                path: "message",
                component: MessagesComponent,
                canActivate: [ComponentGuard]
            },
            {
                path: "user",
                component: UserDetilsComponent,
                canActivate: [ComponentGuard]
            },
            {
                path :"imgEnc",
                component:ImgEncComponent,
                canActivate:[ComponentGuard]
            },
            {
                path :"imgDcrpt",
                component:ImgDecryptComponent,
                canActivate:[ComponentGuard]
            },
            {
                path :"docEnc",
                component:DocEncComponent,
                canActivate:[ComponentGuard]
            },
            {
                path :"docDcrpt",
                component:DocDecryptComponent,
                canActivate:[ComponentGuard]
            },
            {
                path :"support",
                component:SupportComponent,
                canActivate:[ComponentGuard]
            },
            {
                path :"gen-key",
                component: GenKeyComponent,
                canActivate:[ComponentGuard]
            },
            
            
        ]},
   
    {
         path: '**', pathMatch: 'full', 
        component: ErrorPageComponent ,
    },

];

@NgModule({
  declarations: [
    HomeComponent,
    ColorsComponent,
    TypographyComponent,
    PanelsComponent,
    TabsComponent,
    AlertsComponent,
    CardsComponent,
    BadgesProgressComponent,
    ListComponent,
    IconsComponent,
    ButtonsComponent,
    
    
  ],
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ 
    RouterModule,
  ]
})

export class AppRoutingModule { }
