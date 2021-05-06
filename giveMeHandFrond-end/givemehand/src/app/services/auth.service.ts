import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../classes/user';
import {environment} from "../../environments/environment";

//const AUTH_API = 'http://localhost:8085/api/auth/';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})



export class AuthService {
  public url =environment.api_url;

  constructor(private http: HttpClient) { 

  }

  login(user : User){
    return this.http.post<User>(this.url + '/auth/signin', {
      email: user.email,
      password: user.password
    }, httpOptions);
  }
 
  register(user : User){
    return this.http.post<User>(this.url+ '/auth/signup', {
      email: user.email,
      password: user.password,
      lastName: user.lastName,
      firstName: user.firstName,
      phoneNumber: user.phoneNumber,
      street: user.street,
      city: user.city,
      zip: user.zip,
      country: user.country
    }, httpOptions);
  }
}
