import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import {  HttpHeaders } from '@angular/common/http';
import { map, catchError, mapTo, tap } from 'rxjs/operators';
import { config } from './config';
import { Tokens } from '../models/tokens';
import { Router } from '@angular/router';
import {HttpResponse} from '@angular/common/http';
import { NgxSpinnerService } from 'ngx-spinner';
import * as CryptoJS from 'crypto-js';


@Injectable()
export class AuthService {

  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private readonly userRole_TOKEN = 'userRole_TOKEN';
  private readonly userName_TOKEN = 'userName_TOKEN';
  private readonly userPerm_TOKEN = 'userPerm_TOKEN';
  private readonly userNumId_TOKEN = 'userNumId_TOKEN';
  private readonly userAgencyId_TOKEN = 'userAgencyId_TOKEN';
  private readonly REFRESH_TOKEN = 'REFRESH_TOKEN';
  private tokenGeneratedAt = 'tokenGeneratedAt';
  private loggedUser: string;
  dcrptMsg: any;
  imgMsg: boolean = false;
  
  constructor(private http: HttpClient,  private router: Router,private spinner: NgxSpinnerService) { }


  login(user){
    return this.http.post(`${config.apiUrl}/login`, user,{observe: 'response'})
    .pipe(
      tap(resp=> {this.doLoginUser(user.username, resp.headers.get('Authorization'),resp.headers.get('UserRole'),resp.headers.get('userId'),resp.headers.get('userNumId'))}), 
      mapTo(true),
      catchError(error => {
        alert("Invalid UserName/Password");
        console.log(error);
        this.spinner.hide();
        return of(false);
    }));
  }

  register(user) {

    let httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'X-Auth-Token', }) }
    this.http.post(`${config.apiUrl}/users/signUp`, user, httpOptions).subscribe(res => {
      console.log(res);
      if (res) {
        alert("Succefully created an account, please Login with your creads!");
        this.router.navigate(['/login']);

      }
    })
  }

  logout() {
        this.doLogoutUser();
        this.spinner.hide();
  }


  isLoggedIn() {
    return (!!this.getJwtToken() && !this.isTokenExpired());
  }

  isTokenExpired(){
    let expired = true;
    let tokenGeneratedAt = localStorage.getItem(this.tokenGeneratedAt);
    let tokenGeneratioDate = new Date(tokenGeneratedAt);
    let currDate = new Date();
    if(currDate < tokenGeneratioDate){
      expired = !expired;
    }
    return expired;
  }

  private doLoginUser(username: string, bearerToken: string, userRoletoken, userId : string,userNumId : string) {
    this.loggedUser = username;
    var userPerms ="";
    let userDetails;
    console.log(userId);
    this.storeTokens(bearerToken, userRoletoken,username,userPerms,userNumId);
  }

  private doLogoutUser() {
    this.loggedUser = null;
    this.removeTokens();
  }

  private storeTokens(bearerToken: string, userRoletoken: string, username :string, userPerms :any, userNumId :string) {
    localStorage.setItem(this.JWT_TOKEN, bearerToken);
    localStorage.setItem(this.userRole_TOKEN, userRoletoken);
    localStorage.setItem(this.userName_TOKEN, username);
    localStorage.setItem(this.userPerm_TOKEN, userPerms);
    localStorage.setItem(this.userNumId_TOKEN, userNumId);
    localStorage.setItem(this.userAgencyId_TOKEN, "Dummy");
    
    let date =  new Date();
    //date.setMinutes(1);
    date.setHours(240);
    localStorage.setItem(this.tokenGeneratedAt, date.toDateString());
    //localStorage.setItem(this.JWT_TOKEN, tokens.jwt_toekn);
    //localStorage.setItem(this.REFRESH_TOKEN, tokens.referesh_token);
  }

  private removeTokens() {
    localStorage.removeItem(this.JWT_TOKEN);
    localStorage.removeItem(this.userRole_TOKEN);
    localStorage.removeItem(this.userName_TOKEN);
    localStorage.removeItem(this.userPerm_TOKEN);
    localStorage.removeItem(this.userNumId_TOKEN);
    localStorage.removeItem(this.userAgencyId_TOKEN);
    localStorage.removeItem(this.tokenGeneratedAt);
    //localStorage.removeItem(this.REFRESH_TOKEN);
    this.router.navigate(['/dashb']);
  }

  refreshToken() {
    return this.http.post<any>(`${config.apiUrl}/refresh`, {
      'refreshToken': this.getRefreshToken()
    }).pipe(tap((tokens: Tokens) => {
      this.storeJwtToken(tokens.jwt_toekn);
    }));
  }

  getJwtToken() {
    return localStorage.getItem(this.JWT_TOKEN);
  }

  getUserRoleToken() {
    return localStorage.getItem(this.userRole_TOKEN);
  }

  setPermissionToken(userPerms :any) {
    localStorage.setItem(this.userPerm_TOKEN, userPerms);
  }
  getUserPermissionToken() {
    return localStorage.getItem(this.userPerm_TOKEN);
  }

  setUserNumId(userNumId :any) {
    localStorage.setItem(this.userNumId_TOKEN, userNumId);
  }
  getUserNumId() {
    return localStorage.getItem(this.userNumId_TOKEN);
  }

  private getRefreshToken() {
    return localStorage.getItem(this.REFRESH_TOKEN);
  }
  public getUserNameToken() {
    return localStorage.getItem(this.userName_TOKEN);
  }
  private storeJwtToken(jwt: string) {
    localStorage.setItem(this.JWT_TOKEN, jwt);
  }
  fetchPermissionByRoleId(roleId){
    return this.http.post(`${config.apiUrl}/role/fetchpermNm/${roleId}`, {});
  }
  fetchPermissionByRoleNm(roleName){
    return this.http.post(`${config.apiUrl}/role/fetchpermNmByRoleNm/${roleName}`, {});
  }
  fetchUserDetails(userId){
    return this.http.post(`${config.apiUrl}/users/fetchUId/${userId}`, {});
  }
  getInqury(Obj){
    let httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'X-Auth-Token', }) }
    return this.http.post(`${config.apiUrl}/support/getInqury`,Obj,httpOptions).subscribe(res =>{
      alert("Thank you for reachinng toward us , our tech team will reach you soon !")
      this.spinner.hide();
    });
  } 

  getMsgInqury(Obj){
    let httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'X-Auth-Token', }) }
    return this.http.post(`${config.apiUrl}/support/getSupport`,Obj,httpOptions);

  }

  setDcrptMsg(data){
    this.dcrptMsg = data;
  }
  getMsg(){
    return this.dcrptMsg;
  }
  setDcrptMsgForImg(){
    this.imgMsg = true;
  }
  getImgMsg(){
    return this.imgMsg;
  }
  getEncrptData(text,key){
    try {
      return CryptoJS.AES.encrypt(JSON.stringify(text),key).toString();
    } catch (e) {
      console.log(e);
    }
  }


  getDrcrptData(text,key){
    try {
      const bytes = CryptoJS.AES.decrypt(text,key);
      if (bytes.toString()) {
        return JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
      }
      return text;
    } catch (e) {
      console.log(e);
    }
  }

  sendOTP(userID){
    let httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'X-Auth-Token', }) }
    return this.http.post(`${config.apiUrl}/users/sendOTPThroughEmail`,userID,httpOptions);
  }
  submitOtp(otp){
    return this.http.post(`${config.apiUrl}/users/checkOtp`,otp, { observe : 'response'  });
  }
  getUser(user){
    let httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'X-Auth-Token', }) }
    return this.http.post(`${config.apiUrl}/users/fetchUser/${user}`,httpOptions,{});
  }

  resetPw(user) {

    let httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'X-Auth-Token', }) }
    return this.http.post(`${config.apiUrl}/users/UpdPwd`, user, httpOptions);
  }


  
}
