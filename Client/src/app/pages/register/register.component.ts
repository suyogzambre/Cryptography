import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HelperService } from '../../_services/helper.service';
import { DataService } from '../../_services/data.service';
import { AuthService } from '../../_services/auth.service';
import { emit } from 'process';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  loginForm: FormGroup;
  constructor(private authservices : AuthService,private dataService: DataService, private formBuilder: FormBuilder ,private plugAndPlay:HelperService) { }

  ngOnInit(){
    this.loginForm = this.formBuilder.group({
      userId: ['',Validators.required],
      mobileNo : ['',Validators.required],
      password : ['',Validators.required],
      rePassword :['',Validators.required],
      checkBox :['',Validators.required],
      emailId :['',Validators.required]
  });
  }
  onSubmit(){
    if(this.loginForm.controls.checkBox.value == false){
      alert("Please Accpet the terms and conditions.")
    }
    
    var controls = this.loginForm.controls;
    if(controls.password.value != controls.rePassword.value){
      alert("Password must be same !");
      return
    }
    var Obj = {
      userId :controls.userId.value,
      mobileNo : controls.mobileNo.value,
      password : controls.password.value,
      emailId :controls.emailId.value,
      role : "user",
      userType : "user",
    };
    
    
    if(Obj.mobileNo.toString().length != 10){
      alert("Mobile Number must be of 10 digit only");
      return;
    }

    if(this.is_email(this.loginForm.controls.emailId.value) == false){
      alert("Please check your email is correctling mention");
      return
    }if(Obj.password.length < 8 ){
      alert("Please use a strong password!")
      return;
    }

    if (this.loginForm.invalid) {
      alert("Invalid input, please fill all the required fields correctly");
      return;
    } else {
      // return;
      this.authservices.register(Obj);
    }

  }

  is_email(email){      
    var emailReg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailReg.test(email); }

}
