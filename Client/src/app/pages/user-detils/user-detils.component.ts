import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HelperService } from '../../_services/helper.service';
import { AuthService } from '../../_services/auth.service';
import { DataService } from '../../_services/data.service';
import { DomSanitizer } from '@angular/platform-browser';
// import {img} from '../../../assets/img/profile.png'
import { NgxSpinnerService } from "ngx-spinner";

@Component({
  selector: 'app-user-detils',
  templateUrl: './user-detils.component.html',
  styleUrls: ['./user-detils.component.css']
})
export class UserDetilsComponent implements OnInit {
  userNumId: string;
  userDetials :any;
  userDetialForm: FormGroup;
  forgotPw = false;
  profilePic: any;
  profilePicNull: boolean = false;

  constructor( private plugAndPlay: HelperService,
    private authService: AuthService, private dataService: DataService, 
    private formBuilder: FormBuilder,
    private domSanitizer: DomSanitizer,private spiner: NgxSpinnerService) { }

  ngOnInit() {
    this.userNumId = localStorage.getItem('userNumId_TOKEN');
    
    this.userDetialForm = this.formBuilder.group({
      fName:[''],
      lName : ['',],
      mobileNo : [''],
      password : [''],
      emailId : [''],
      state : [''],
      city : [''],
      isActive : [''],
      role : [''],
      userId : [''],
      userType : [''],
      fatherName : [''],
      completeName : [''],
      userNumId :[''],
      profile_pic:['']
    });

    this.dataService.getLogedInUserDetials(this.userNumId).subscribe(data => {
      this.userDetials = data;
      if(data){
        this.loadBaseData();
      
      }
    });

  }

  loadBaseData(){
    if(this.userDetials.profile_pic == null || this.userDetials.profile_pic == undefined || this.userDetials.profile_pic == ""){
      this.profilePicNull = true;
    }else{
      this.dataService.getProfilePic(this.userDetials.userNumId).subscribe(res =>{
        const objectURL = URL.createObjectURL(res.body);
        const img = this.domSanitizer.bypassSecurityTrustUrl(objectURL);
        this.profilePic = img ;
      });
    }

    this.userDetialForm = this.formBuilder.group({
      userNumId:[this.userDetials.userNumId],
      fName:[this.userDetials.fName],
      lName : [this.userDetials.lName],
      mobileNo : [this.userDetials.mobileNo],
      password : [this.userDetials.password],
      emailId : [this.userDetials.emailId],
      state : [this.userDetials.state],
      city : [this.userDetials.city],
      isActive : [this.userDetials.isActive],
      role : [this.userDetials.role],
      userId : [this.userDetials.userId],
      userType : [this.userDetials.userType],
      fatherName : [this.userDetials.fatherName],
      completeName : [this.userDetials.fName +" "+ this.userDetials.lName],      
      profile_pic :[this.userDetials.profile_pic]            
    });
  }
  forgotPwf(){
    this.forgotPw = true;
  }


  onSubmit(){
    this.spiner.show();
    var controls = this.userDetialForm.controls;
    var Obj = {
      userNumId:controls.userNumId.value,
      fName:controls.fName.value,
      lName : controls.lName.value,
      mobileNo : controls.mobileNo.value,
      password : controls.password.value,
      emailId : controls.emailId.value,
      state : controls.state.value,
      city : controls.city.value,
      isActive : controls.isActive.value,
      role : controls.role.value,
      userId : controls.userId.value,
      userType : controls.userType.value,
      fatherName : controls.fatherName.value,
      completeName : controls.completeName.value,
      profile_pic:controls.profile_pic.value,
    };


    if(Obj.mobileNo.toString().length != 10){
      alert("Mobile Number must be of 10 digit only");
      this.spiner.hide();
      return;
    }
    if(this.is_email(Obj.emailId) == false){
      alert("Please check your email is correctling mention");
      this.spiner.hide();
      return
    }if(Obj.password.length < 8 ){
      alert("Please use a strong password!");
      this.spiner.hide();
      return;
    }

    if(!this.userDetialForm.valid){
      alert("Please fill all the fields");
      this.spiner.hide();
      return;
    } else {
      // return;
      this.dataService.updteProfile(Obj).subscribe(res =>{
        if(res){
          alert("Profile Updated succefully ");
          this.spiner.hide();
          this.ngOnInit();
        }
      });
    }

    
  }  


  setProfilePic(selectedFile: File){
    this.spiner.show();
    var data = new FormData();
    data.append("userNumId", this.userDetials.userNumId);
    data.append("file", selectedFile[0]);
  
  console.log(selectedFile[0].name);
    this.dataService.setProfilePic(data).subscribe(res=>{
      if(res){
        alert("profile succefully updated");
        this.spiner.hide();
        this.ngOnInit();
      }
    })
  }

  is_email(email){      
    var emailReg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailReg.test(email); }

}
