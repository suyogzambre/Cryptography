import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
// import { retry } from 'rxjs-compat/operator/retry';
import { AuthService } from '../../_services/auth.service';
import { DataService } from '../../_services/data.service';
// import * as CryptoJS from 'crypto-js';
import { NgxSpinnerService } from "ngx-spinner";

@Component({
  selector: 'app-text-enc',
  templateUrl: './text-enc.component.html',
  styleUrls: ['./text-enc.component.css']
})
export class TextEncComponent implements OnInit {
  userDetils: any;
  txtEncForm: FormGroup;
  userDetilsShow: boolean = false;
  userDetilBtnShow: boolean = true;
  userEnterdKey :any;

  constructor(private dataService: DataService, private spinner: NgxSpinnerService,private formBuilder: FormBuilder , private auth : AuthService) { }

  ngOnInit() {
    this.txtEncForm = this.formBuilder.group({
      text: ['', Validators.required],
      userId: ['', Validators.required],
      key: ['', Validators.required],
      multiSend: ['']
    });

    var time = new Date();
  }

  getUserDetails() {
    this.spinner.show();
    let userId = this.txtEncForm.value.userId;
    if (userId == "") {
      alert("Please Enter the userId");
      this.userDetilsShow = false;
      this.spinner.hide();
      return;
    }
    this.dataService.getUserDetails(userId).subscribe(data => {
      this.userDetils = data;
      if (!this.userDetils) {
        this.userDetilsShow = false;
        alert("Please Enter Valid User ID");
        this.spinner.hide();
      } else {
        this.userDetilsShow = true;
        this.userDetils = data;
        this.spinner.hide();

      }
    });
  }

  onSubmit() {
    this.spinner.show();

    if (this.txtEncForm.controls.multiSend.value == false) {

      console.log(this.txtEncForm);
      let currentDateTime = moment().format("MM/DD/YYYY hh:mm:ss");
      var controls = this.txtEncForm.controls;
      var Obj = {
        text: controls.text.value,
        key: controls.key.value,
        userId: controls.userId.value,
        time: currentDateTime,
        senderName: localStorage.getItem('userNumId_TOKEN'),
      };
      if (this.txtEncForm.invalid) {
        alert("Invalid input, please fill all the required fields correctly");
        this.spinner.hide();

        return;
      } else {

        this.dataService.getUserDetails(Obj.userId).subscribe(data => {
          this.userDetils = data;
          if (this.userDetils == null) {
            this.userDetilsShow = false;
            alert("Please Enter Valid User ID");
            this.spinner.hide();

          } else {
            if (confirm("Are you sure you want to send the message to " + Obj.userId + " .")) {
              console.log(Obj);
              try {
                let encrpted_Text = this.auth.getEncrptData(Obj.text,Obj.key);
                if (encrpted_Text) {
                  Obj['text'] = encrpted_Text;
                  console.log("The text is :" ,encrpted_Text);
                  this.spinner.hide();

                }
              } catch (error) {
                console.log(ErrorEvent);
                this.spinner.hide();

              }
              console.log(Obj);
              this.dataService.encText(Obj).subscribe((res: any) => {
                if (res) {
                  alert("Done 👍. Message Send SuccessFully !");
                  this.userDetilsShow = false;
                  this.spinner.hide();

                };
              });
            }
            this.spinner.hide();
          }
        });
      }
    } else {
      let currentDateTime = moment().format("MM/DD/YYYY hh:mm:ss");
      var controls = this.txtEncForm.controls;
      var Obj = {
        text: controls.text.value,
        key: controls.key.value,
        userId: controls.userId.value,
        time: currentDateTime,
        senderName: localStorage.getItem('userNumId_TOKEN'),
      };
      if (this.txtEncForm.invalid) {
        alert("Invalid input, please fill all the required fields correctly");
        this.spinner.hide();
        return;
      } else {
        if (confirm("Are you sure you want to send the message to " + Obj.userId + " .")) {
          console.log(Obj);

          try {
            this.userEnterdKey = Obj.key;
            let newKey = this.auth.getEncrptData(Obj.key,Obj.key);
            if (newKey) {
              Obj['key'] = newKey;
            }
            let encrpted_Text = this.auth.getEncrptData(Obj.text,this.userEnterdKey);
            if (encrpted_Text) {
              Obj['text'] = encrpted_Text;
            }
          } catch (error) {
            console.log(ErrorEvent);
          }
          console.log(Obj);

          this.dataService.encText(Obj).subscribe((res: any) => {
            if (res) {
              alert("Done 👍. Message Send SuccessFully !");
              this.userDetilsShow = false;
              this.spinner.hide();

            };
          });
        }

      }
    }

  }

  onChangeMultiSend(desi) {
    if (desi.srcElement.checked == false) {
      this.userDetilBtnShow = true;
    } else {
      this.userDetilBtnShow = false;
      this.userDetilsShow = false;
    }
  }

  }
