import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import * as moment from 'moment';

import { NgxSpinnerService } from "ngx-spinner";
import { DataService } from './data.service';

@Injectable()
export class HelperService {

  constructor(private spinner: NgxSpinnerService, private dataService: DataService) { }
  invokeGeneralEvent: Subject<any> = new Subject();
  invokeDataEvent: Subject<any> = new Subject();
  invokTicketEvent: Subject<any> = new Subject();
  invokeCurrDrawDataEvent: Subject<any> = new Subject();
  invokePrevDrawDataEvent: Subject<any> = new Subject();
  slectedGame = "";

  getMinDate(minDays){
    let d = new Date();
    if(minDays < 0){
      minDays = minDays*(-1);
      d.setDate(d.getDate()+minDays);
    } else {
      d.setDate(d.getDate()-minDays);
    }
    return d.getFullYear()+"-"+(d.getMonth() < 10 ? "0"+(d.getMonth()+1) : d.getMonth()+1) +"-"+ (d.getUTCDate() < 9 ? "0"+(d.getUTCDate()+1) : d.getUTCDate()+1);
  }

  getMaxDate(maxDays){
    let d = new Date();
    d.setDate(d.getDate()+maxDays);
    return d.getFullYear()+"-"+(d.getMonth() < 10 ? "0"+(d.getMonth()+1) : d.getMonth()+1) +"-"+ (d.getUTCDate() < 9 ? "0"+(d.getUTCDate()+1) : d.getUTCDate()+1);
  }

  getCurrentDrawTime(){
    let now = moment();
    let mm = now.minutes();
    let hr = now.format('hh:mm:ss a').substring(0,2);
    let startCount = 0;
    let a = now.format('h:mm:ss a');
    let drawTime;
    a =  a.substring(a.length-2,a.length);
    a = a.toLocaleUpperCase();
    if(mm >= 0 && mm < 15){
      startCount = 15 - mm;
      drawTime = hr.toString()+":15:00 "+a;
    } else if(mm >= 15 && mm < 30){
      startCount = 30 - mm;
      drawTime = hr.toString()+":30:00 "+a;
    } else if(mm >= 30 && mm < 45){
      startCount = 45 - mm;
      drawTime = hr.toString()+":45:00 "+a;
    } else {
      startCount = 60 - mm;
      let hh = Number(hr);
      hh = hh + 1;

      drawTime = hh < 10 ? "0"+hh.toString()+":00:00 "+a : hh.toString()+":00:00 "+a;
    }

    return {"drawTime": drawTime, "startCount": startCount};
  }
  get10GharCurrentDrawTime(systemTime){
    let now = moment();
    let mm = new Date(systemTime).getMinutes();//now.minutes();
    let hr = now.format('hh:mm:ss a').substring(0,2);
    let startCount = 0;
    let a = now.format('h:mm:ss a');
    let drawTime;
    a =  a.substring(a.length-2,a.length);
    a = a.toLocaleUpperCase();
    if(mm >= 0 && mm < 5){
      startCount = 5 - mm;
      drawTime = hr.toString()+":05:00 "+a;
    } else if(mm >= 5 && mm < 10){
      startCount = 10 - mm;
      drawTime = hr.toString()+":10:00 "+a;
    } else if(mm >= 10 && mm < 15){
      startCount = 15 - mm;
      drawTime = hr.toString()+":15:00 "+a;
    } else if(mm >= 15 && mm < 20){
      startCount = 20 - mm;
      drawTime = hr.toString()+":20:00 "+a;
    } else if(mm >= 20 && mm < 25){
      startCount = 25 - mm;
      drawTime = hr.toString()+":25:00 "+a;
    } else if(mm >= 25 && mm < 30){
      startCount = 30 - mm;
      drawTime = hr.toString()+":30:00 "+a;
    } else if(mm >= 30 && mm < 35){
      startCount = 35 - mm;
      drawTime = hr.toString()+":35:00 "+a;
    } else if(mm >= 35 && mm < 40){
      startCount = 40 - mm;
      drawTime = hr.toString()+":40:00 "+a;
    } else if(mm >= 40 && mm < 45){
      startCount = 45 - mm;
      drawTime = hr.toString()+":45:00 "+a;
    } else if(mm >= 45 && mm < 50){
      startCount = 50 - mm;
      drawTime = hr.toString()+":50:00 "+a;
    } else if(mm >= 50 && mm < 55){
      startCount = 55 - mm;
      drawTime = hr.toString()+":55:00 "+a;
    } else {
      startCount = 60 - mm;
      let hh = Number(hr);
      hh = hh + 1;
      drawTime = hh < 10 ? "0"+hh.toString()+":00:00 "+a : hh.toString()+":00:00 "+a;
    }
    return {"drawTime": drawTime, "startCount": startCount};
  }
  getLastDrawTime(){
    let now = moment();
    let mm = now.minutes();
    let hr = now.format('hh:mm:ss a').substring(0,2);
    let startCount = 0;
    let a = now.format('h:mm:ss a');
    let drawTime;
    a =  a.substring(a.length-2,a.length);
    a = a.toLocaleUpperCase();
    if(mm == 0 || (mm > 0 && mm < 5)){
      //let hh = Number(hr);
      drawTime = hr.toString()+":00 "+a;
    } else if(mm > 15 && mm < 30){
      drawTime = hr.toString()+":15 "+a;
    } else if(mm > 30 && mm < 45){
      drawTime = hr.toString()+":30 "+a;
    } else {
      drawTime = hr.toString()+":45 "+a;
    }
    return drawTime;
  }

  getTimeFromGameString(gameTimeString){
    gameTimeString = gameTimeString.substr(9);
    let hh = gameTimeString.substr(0,2);
    let mm = gameTimeString.substr(2);
    if(hh > 12 && hh == 13){
      return "01"+":"+mm+" PM";
    } else if(hh > 12 && hh == 14){
      return "02"+":"+mm+" PM";
    } else if(hh > 12 && hh == 15){
      return "03"+":"+mm+" PM";
    } else if(hh > 12 && hh == 16){
      return "04"+":"+mm+" PM";
    } else if(hh > 12 && hh == 17){
      return "05"+":"+mm+" PM";
    } else if(hh > 12 && hh == 18){
      return "06"+":"+mm+" PM";
    } else if(hh > 12 && hh == 19){
      return "07"+":"+mm+" PM";
    } else if(hh > 12 && hh == 20){
      return "08"+":"+mm+" PM";
    } else if(hh > 12 && hh == 21){
      return "09"+":"+mm+" PM";
    } else {
      return hh+":"+mm+" AM";
    }
  }

  // getGameBySlot(gameSlot, drawObj: any){
  //   this.spinner.show(); 
  //   let today = this.getDateInYYMMDD(new Date(), "");
  //   let gameSlotObj = {
  //     curr: "curr",
  //     prev: "prev"
  //   }
  //   this.dataService.getGames(today).subscribe((data: any)=>{
  //     console.log("games fetch resp: ", data);
  //     for (const key in gameSlotObj) {
  //       const gameSlot = gameSlotObj[key];
  //       let draw: any;
  //       if(gameSlot == 'curr'){
  //         draw = this.getCurrentDrawTime().drawTime;
  //       } else if(gameSlot == 'prev'){
  //         draw = this.getLastDrawTime();
  //       }
  //       let currHH = draw.substring(0,2);
  //       let currMM = draw.substring(draw.indexOf(':')+1, draw.indexOf(':')+3);
  //       let game:any;
  //       for (let i = 0; i < data.length; i++) {
  //         const gameElement = data[i];
  //         let gameNumber = gameElement.gameNumber;
  //         let hh = gameNumber.substring(gameNumber.indexOf('-')+1,gameNumber.indexOf('-')+3);
  //         let mm = gameNumber.substring(gameNumber.indexOf('-')+3,gameNumber.length);

  //         if(currHH == hh && currMM == mm){
  //           game = gameElement;
  //           break;
  //         } else if(currHH == '01' && hh == '13' && currMM == mm){
  //           game = gameElement;
  //           break;
  //         } else if(currHH == '02' && hh == '14' && currMM == mm){
  //           game = gameElement;
  //           break;
  //         } else if(currHH == '03' && hh == '15' && currMM == mm){
  //           game = gameElement;
  //           break;
  //         } else if(currHH == '04' && hh == '16' && currMM == mm){
  //           game = gameElement;
  //           break;
  //         } else if(currHH == '05' && hh == '17' && currMM == mm){
  //           game = gameElement;
  //           break;
  //         } else if(currHH == '06' && hh == '18' && currMM == mm){
  //           game = gameElement;
  //           break;
  //         } else if(currHH == '07' && hh == '19' && currMM == mm){
  //           game = gameElement;
  //           break;
  //         } else if(currHH == '08' && hh == '20' && currMM == mm){
  //           game = gameElement;
  //           break;
  //         }
  //       }
  //       drawObj[gameSlot] = drawObj[gameSlot] || {};
  //       drawObj[gameSlot] = game;
  //     }
  //    // if(Object.keys(drawObj).length > 0 && (drawObj.curr != null && drawObj.curr != undefined && drawObj.prev != null && drawObj.prev != undefined)){
  //       this.invokeCurrDrawDataEvent.next(drawObj["curr"]);
  //       this.invokePrevDrawDataEvent.next(drawObj["prev"]);  
  //       this.spinner.hide();
  //       return;
  //     //}
  //   });
  // }

  getDateInYYMMDD(date, separator){
    if (date == null || date == undefined || date == ""){
      return null;
    }else{
    let d = new Date(date);
    let yy = d.getFullYear();
    let mm = d.getMonth()+1;
    let month = mm < 10 ? "0"+mm : mm;
    let dd = d.getDate();
    let day = dd < 10 ? "0"+dd : dd;
    return yy+separator+month+separator+day;
  }

  }
  getDateInDDMMYY(date, separator){
    let d = new Date(date);
    let yy = d.getFullYear();
    let mm = d.getMonth()+1;
    let dd = d.getDate();
    return dd+separator+mm+separator+yy;
  }

  getTimeFromDate(date){
    let d = new Date(date);
    let hr = (d.getHours() < 10 ? '0' : '')+ d.getHours();
    let min = (d.getMinutes() < 10 ? '0' : '')+ d.getMinutes();
    return hr+":"+min;
  }

  convertDateTimeToUTCTimeZone(dateInput){
    let date = new Date(dateInput);
    const off    = date.getTimezoneOffset()
    const absoff = Math.abs(off)
    return (new Date(date.getTime() - off*60*1000).toISOString().substr(0,23) +
            (off > 0 ? '-' : '+') + 
            Math.floor(absoff / 60).toFixed(0).padStart(2,'0') + ':' + 
            (absoff % 60).toString().padStart(2,'0'))
  }

  getDateCommonformat(date, separator){
    let d = new Date(date);
    let yy = d.getFullYear();
    let mm = ((d.getMonth() + 1) < 10 ? '0' : '') + (d.getMonth() + 1);
    let dd = (d.getDate() < 10 ? '0' : '')+ d.getDate();
    let hr = (d.getHours() < 10 ? '0' : '')+ d.getHours();
    let min = (d.getMinutes() < 10 ? '0' : '')+ d.getMinutes();
    return dd+separator+mm+separator+yy+" "+hr+":"+min;
  }

  getTimeInLocalFormat(date){
    return date.toLocaleTimeString();
  }
  printTicket(){
    this.invokeGeneralEvent.next("print");
  }

  clearAllSelection(){
    this.invokeGeneralEvent.next("clear");
  }

  getResults(){
    this.invokeDataEvent.next("getResult");
  }

  search(term: any, columnName: string, columnMap: any, filterApplied: boolean, filteredData: any, data: any, event:any ,type:string) {
    if(event.key === "Tab"){
      return
    }
    var selectedColumn = columnMap[columnName];
    if (term == undefined || term == null  || term == "" ) {
      filteredData = data;
    } else {
      if (type == "string"){
      filteredData = data.filter(x =>
        x[selectedColumn] ? x[selectedColumn].trim().toLowerCase().includes(term.trim().toLowerCase()) : ""
      );
      console.log(data)
      filterApplied = true;
    }
    else if (type == "number"){
      term = Number(term);
      filteredData = data.filter(x =>
        x[selectedColumn] ? x[selectedColumn].toString().trim().includes(term.toString().trim()) : ""
      );
      console.log(data)
      filterApplied = true;
    }
    }
    return {'filteredData': filteredData, 'filterApplied': filterApplied};
    
  }


  convToArrOrToCommaSep(strData){

    let result="";
    if(Array.isArray(strData)){
    let data=strData;
    result = data.map(function(val) {
      return val;
    }).join(',');
    }else{
      result=strData.split(",");
    }
    console.log(result);
    return result;
  }
  
  fetchBlobTypefromExt(fileExt){
//MIME type for every extension
    //ordered the blobType as per usuage
    var blobType="";
      if(fileExt == ".pdf")
      blobType = 'application/pdf';
      else if(fileExt == ".jpg" || fileExt == ".jpeg")
      blobType ='image/jpeg';
      else if(fileExt == ".tif" || fileExt == ".tiff")
      blobType = 'image/tiff';
      else if(fileExt == ".docx" )
      blobType="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
      else if(fileExt == ".xlsx" )
      blobType="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
      else if(fileExt == ".png")
      blobType ='image/png';
      else if(fileExt == ".doc")
      blobType="application/msword";
      else if(fileExt == ".xls")
      blobType="application/vnd.ms-excel";
      else if(fileExt == ".zip")
      blobType ='application/zip';
      else if(fileExt == ".png")
      blobType ='image/png';
      else
      blobType ='image/jpeg';
      console.log(fileExt||'....'||blobType);
      return blobType;
  }

  downloadRestFile(resp){

    var fileName = resp.headers.get('Content-Disposition');
    if (!fileName) {
      alert("Please check your Key ")
      return;
    }

      //var respType = resp.headers.get('Content-Type');
      fileName = fileName.substr(fileName.indexOf("=") + 1);
      //var newBlob = new Blob([resp.body], { type: "application/vnd.openxmlformats-officedocument.wordprocessingml.document" }); //"application/vnd.openxmlformats-officedocument.wordprocessingml.document"//"application/pdf"
  
      var fileExt=fileName.substring(fileName.indexOf(".",-1)).toLowerCase().trim();
      //  fileExt=fileExt.low
      fileName=fileName.trim()
      let contentType = this.fetchBlobTypefromExt(fileExt);
      let newBlob = new Blob([resp.body], {type:contentType});

      const nav = (window.navigator as any);
      if (nav && nav.msSaveOrOpenBlob) {
        nav.msSaveOrOpenBlob(newBlob);
        return;
      }
      const data = window.URL.createObjectURL(newBlob);
  
      var link = document.createElement('a');
      link.style.display = "none";
      if (typeof link.download === "undefined") {
        //window.location = data;
      }
      else {
        link.href = data;
        link.download = fileName;
        document.body.appendChild(link);
        link.click();
      }
      setTimeout(function () {
        window.URL.revokeObjectURL(data);
      }, 100);
    
  }

  getStaticDataLocal(staticDataType) : JSON{
    let jsonResult : any ={};
    if(staticDataType === "CaseAreaType"){
      jsonResult["RET"]="Retail";
      jsonResult["CORP"]="Corporate";
    }else if(staticDataType === "CompanyType"){
      jsonResult["INS"]="Insurance Company";
      jsonResult["TPA"]="TPA";
      jsonResult["Corporate"]="Corporate";
    }else if(staticDataType === "UserType"){
      jsonResult["FO"]="Field Officer";
      jsonResult["RM"]="Report Manager";
      jsonResult["CM"]="Claim Manager";
      jsonResult["SCM"]="Senior Claim Manager";
      jsonResult["ADMIN"]="Admin User";
    }else if(staticDataType === "ReportNames"){
      jsonResult["DailyTrackerReport"]="Daily Tracker Report";
      //jsonResult["CaseOpenWithInvestigators"]="CASES OPEN WITH INVESTIGATORS";
      //jsonResult["PaymentMadeToInvestigators"]="PAYMENT MADE TO INVESTIGATORS";
      //jsonResult["reportSendToCompany"]="CASES REPORTS SEND TO COMPANY";
      //jsonResult["caseBillAndPaymentReceived"]="CASES BILLED AND PAYMENT RECEIVED";
    }else if(staticDataType === "HospitalStatus"){
      jsonResult["ACT"]="Active";
      jsonResult["INACT"]="InActive";
      jsonResult["FRD"]="Fraud";
    }else if(staticDataType === "clientType"){
      jsonResult["INS_COMP"]="Insurance Company Value";
      jsonResult["TPA"]="TPA";
      jsonResult["AGENCY"]="Agency Company";
    }
    return jsonResult;
  }

  getStaticCaseStatusByScreen(screenNm): JSON{
    let jsonResult : any ={};
    //let allCaseStatus=['NewCase','Allocated','ReportToBePrepared','Completed','Closed','HardcopiesReceived','Settled','Reinvestigation']
    if(screenNm === "GenerateCase"){
      jsonResult=['NewCase']
    }else if(screenNm === "ViewCase"){
      jsonResult=['Allocated','ReportToBePrepared','Completed','Closed','HardcopiesReceived','Settled','Reinvestigation']
    }
    return jsonResult;
  }
  calculateDiff(dateSent){
    let currentDate = new Date();
    dateSent=dateSent?dateSent:currentDate;
    dateSent = new Date(dateSent);

    return Math.floor((Date.UTC(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()) - Date.UTC(dateSent.getFullYear(), dateSent.getMonth(), dateSent.getDate()) ) /(1000 * 60 * 60 * 24));
  }


  calculateDiffInDaysHrsMins(dateSent){
    let currentDate = new Date();
    dateSent=dateSent?new Date(dateSent):currentDate;
    
    const currDt = Date.UTC(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate(),currentDate.getHours(),currentDate.getMinutes());
    const endDt = Date.UTC(dateSent.getFullYear(), dateSent.getMonth(), dateSent.getDate(),dateSent.getHours(),dateSent.getMinutes());
    
    let ms= currDt - endDt;
    //let ms = currentDate - dateSent;
    return this.convMiliSecondToDateformat(ms);
  }

  convMiliSecondToDateformat(ms){
    const _MS_PER_DAY = 1000 * 60 * 60 * 24;
    let days = Math.floor(ms / _MS_PER_DAY)+ " day";
    let daysms=ms % (_MS_PER_DAY);
    let hours = Math.floor((daysms)/(60*60*1000))+ " hr";
    let hoursms=ms % (60*60*1000);
    let minutes = Math.floor((hoursms)/(60*1000))+ " min";
    let minutesms=ms % (60*1000);
    let sec = Math.floor((minutesms)/(1000));
    return days+" "+String(hours).padStart(2,'0')+" "+String(minutes).padStart(2,'0');
  }

  isDocDeletionAllowed(attachement): boolean {
    if(attachement.status == 'P' && attachement.attachmentType!='AUTH_LETTER' && attachement.attachmentType!='SUPPDOC' && attachement.attachmentType!='AUTH_LETTER_PATIENT' && attachement.attachmentType!='AUTH_LETTER_HOSPITAL' && attachement.attachmentType!='AUTH_LETTER_DOCTOR' && attachement.attachmentType!='AUTH_LETTER_PATHOLOGY' && attachement.attachmentType!='AUTH_LETTER_PHARMACY')
    return true
    else
    return false
  }

}