import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { config } from './config';
import { Observable, of } from 'rxjs';
import { environment } from '../../environments/environment'

export interface storeCaseResp {
  statusCode: any;
  obj: any;
  status: string
}

@Injectable()
export class DataService {
  
  constructor(private http: HttpClient) { }
  
  httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'X-Auth-Token','X-FORWARDED-FOR':'*' }) }
  signUp(user){
    return this.http.post(`${config.apiUrl}/users/signUp`, user, { observe: 'response' });
  }
  updSubCity(subCityObj){
    return this.http.post(`${config.apiUrl}/updSubCity`, subCityObj, { observe: 'response' });
  }
  getUserDetails(userId){
    return this.http.get(`${config.apiUrl}/users/fetchUA/${userId}`, {});
  }
  getUserNumId(userId){
    return this.http.get(`${config.apiUrl}/users/fetchUser/${userId}`, {});
  }
  encText(Obj){
    return this.http.post(`${config.apiUrl}/txt/encTxt`, Obj, { observe: 'response' });
  }
  dcrptTxt(obj){
    return this.http.post(`${config.apiUrl}/txt/dcrtTxt`,obj,{ observe: 'response' });
  }
  getMsgs(userNumId){
    return this.http.get(`${config.apiUrl}/msg/getAllMsg/${userNumId}`, {});
  }
  deleteMsg(id){
    return this.http.get(`${config.apiUrl}/msg/deleteMsg/${id}`, {});
  }

  getLogedInUserDetials(userNumId){
    return this.http.get(`${config.apiUrl}/users/fetchUN/${userNumId}`, {});
  }

  getIpAddreess(){
    return this.http.get("http://api.ipify.org/?format=json");
  }
  

  sendEncImage(obj){
    return this.http.post(`${config.apiUrl}/img/sendEncrpt`, obj,{ observe: 'response' });
  }

  encImage(img){
    let fileDownloadHttpoOption = { 'Authorization': 'X-Auth-Token' }
    return this.http.post(`${config.apiUrl}/img/encrpt`,img,{'headers': fileDownloadHttpoOption, responseType: 'blob', observe: "response" });
  }
  dcrptImg(img){
    let fileDownloadHttpoOption = { 'Authorization': 'X-Auth-Token' }
    return this.http.post(`${config.apiUrl}/img/dcrpt`,img,{'headers': fileDownloadHttpoOption, responseType: 'blob', observe: "response" });
  }
  dcrptMsgImg(img){
    let fileDownloadHttpoOption = { 'Authorization': 'X-Auth-Token' }
    return this.http.post(`${config.apiUrl}/img/dcrptMsgImg`,img,{'headers': fileDownloadHttpoOption, responseType: 'blob', observe: "response" });
  }
  sendOTP(userID){
    return this.http.post(`${config.apiUrl}/users/sendOTPThroughEmail`,userID,{ observe: 'response' });
  }

  submitOtp(otp){
    return this.http.post(`${config.apiUrl}/users/checkOtp`,otp, { observe : 'response'  });

  }
  resetPw(user){
    return this.http.post(`${config.apiUrl}/users/UpdPwd`,user,{ observe: 'response' });

  }
  updteProfile(user){
    return this.http.post(`${config.apiUrl}/users/updateUser`,user,{ observe: 'response' });

  }
  setProfilePic(file){
    return this.http.post(`${config.apiUrl}/users/setProfilePic`,file,{ observe: 'response' });

  }

    getProfilePic(userNumId){
    let fileDownloadHttpoOption = { 'Authorization': 'X-Auth-Token' }
    return this.http.get(`${config.apiUrl}/users/getProfilePic/${userNumId}`,{'headers': fileDownloadHttpoOption, responseType: 'blob', observe: "response" });
  }

  fetchAllUser(){
    return this.http.get(`${config.apiUrl}/users/fetchUA`,{});
  }
  



}
