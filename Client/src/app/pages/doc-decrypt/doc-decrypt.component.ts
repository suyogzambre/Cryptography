import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { file } from 'jszip';
import { HelperService } from '../../_services/helper.service';
import { DataService } from '../../_services/data.service';
import { AuthService } from '../../_services/auth.service';

@Component({
  selector: 'app-doc-decrypt',
  templateUrl: './doc-decrypt.component.html',
  styleUrls: ['./doc-decrypt.component.css']
})
export class DocDecryptComponent implements OnInit {

  imgDcrptForm: FormGroup;
  selectedFile: any;
  senderName: string;
  dcrptObj: any;
  msgImgDcrpt: boolean = true;
  isMsg: boolean;
  constructor(private authService: AuthService, private dataService: DataService, private formBuilder: FormBuilder, private plugAndPlay: HelperService) { }

  ngOnInit() {

    this.dcrptObj = this.authService.getMsg();
    this.isMsg = this.authService.getImgMsg();

    this.imgDcrptForm = this.formBuilder.group({
      id : [''],
      file: [file],
      key: ['', Validators.required],
      filePath: [''],
    });
    this.senderName = localStorage.getItem('userName_TOKEN')

    if (this.isMsg == false) {
      this.msgImgDcrpt = false;
    } else {
      this.msgImgDcrpt = true;
      this.imgDcrptForm.controls['filePath'].setValue(this.dcrptObj.filePath);
    }
  }
  onSubmit() {
    var controls = this.imgDcrptForm.controls;
    var Obj = {
      id : controls.id.value,
      key: controls.key.value,
      filePath: controls.filePath.value,
    };
    if (this.imgDcrptForm.invalid) {
      alert("Invalid input, please fill all the required fields correctly");
      return;
    } else {
      if (this.msgImgDcrpt == true) {
        this.dcrptMsgImg(Obj.filePath, Obj.key, this.senderName,Obj.id)
      } else {
        this.dcrptData(this.selectedFile, Obj.key, this.senderName);
      }
    }
  }
  onChnageFile(file) {
    this.selectedFile = file;

  }
  dcrptData(fileInput, key, senderName) {
    var data = new FormData();
    data.append("key", key);
    data.append("file", fileInput[0], fileInput[0].name);
    data.append("sender", senderName);
    this.dataService.dcrptImg(data).subscribe(res => {

      if (res) {
        this.plugAndPlay.downloadRestFile(res);
      }
    }, error => {
      console.log(error);
    });
  }


  dcrptMsgImg(filePath, key, senderName,id) {
    var data = new FormData();
    data.append("id",id);
    data.append("key", key);
    data.append("filePath", filePath);
    data.append("sender", senderName);
    this.dataService.dcrptMsgImg(data).subscribe(res => {
      if (res) {
        this.plugAndPlay.downloadRestFile(res);
      }
    }, error => {
      console.log(error);
    });
  }
}
