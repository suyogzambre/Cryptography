import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms'; 
import { Router } from '@angular/router';
import { NgxSpinnerService } from "ngx-spinner";

import { AuthService } from '../../_services/auth.service';

declare var $:any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit, AfterViewInit, OnDestroy {

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router, private spinner: NgxSpinnerService) { }

  loginForm: FormGroup;


  ngOnInit() {
    $('body').addClass('empty-layout bg-silver-300');
    this.loginForm = this.formBuilder.group({
      username: [''],
      password: [''],
      userType: [''],
    });
  }

  get f() { return this.loginForm.controls; }

  onLogin() {
    this.spinner.show();
    this.authService.login(
      {
        username: this.f.username.value,
        password: this.f.password.value
      }
    )
    .subscribe(success => {
      if (success) {
        this.spinner.hide();
        this.router.navigate(['/index']);
      }
    },err => {
      this.spinner.hide();
      console.log(err.message);
      if(err.status=403)
      alert("Invalid/username password");
    });
  }

  ngAfterViewInit() {
    $('#login-form').validate({
        errorClass: "help-block",
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true
            }
        },
        highlight: function(e) {
            $(e).closest(".form-group").addClass("has-error")
        },
        unhighlight: function(e) {
            $(e).closest(".form-group").removeClass("has-error")
        },
    });
  }

  ngOnDestroy() {
    $('body').removeClass('empty-layout bg-silver-300');
  }

}
