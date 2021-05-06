import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../classes/user";

const httpOptions = {
  //headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {
 
  private  baseUrl = environment.api_url;

  constructor(private http: HttpClient) { }

  public  updateUser( user: User) : Observable<User>{
    return this.http.put<User>(`${this.baseUrl}/auth/updateUser`,user);
  }

  public getUserByEmail(email: string){
    return this.http.get(`${this.baseUrl}/auth/findUserByemail/${email}`);
  }
  public getUserById(id: number) : Observable<any>{
    return this.http.get(`${this.baseUrl}/auth/finduserById/${id}`);
  }

  getUserList():Observable<any> {
    return this.http.get(this.baseUrl+'/auth/getAllUser');
  }
    deleteUser(id:number):Observable<any> {
    return this.http.delete(this.baseUrl+"/auth/deleteUser/"+id,{ responseType: 'text'});

    }
  findIdUserByMail(email: string) : Observable<any> {
    return this.http.get(`${this.baseUrl}/auth/findIdUserByMail/${email}`);
  }

  public getOffersByUser(idUser : number):Observable<any> {
    return this.http.get(`${this.baseUrl}/auth/getOffersByUser/${idUser}`);
  }
  public getNbAcceptedDemande(idUser : number):Observable<any> {
    return this.http.get(`${this.baseUrl}/auth/getNbAcceptedDemande/${idUser}`);
  }
  public getNbRefusedDemande(idUser : number):Observable<any> {
    return this.http.get(`${this.baseUrl}/auth/getNbRefusedDemande/${idUser}`);
  }
  public getNbWaitingDemande(idUser : number):Observable<any> {
    return this.http.get(`${this.baseUrl}/auth/getNbWaitingDemande/${idUser}`);
  }
  getMedaillesUserByEmail(email: string) : Observable<any> {
    return this.http.get(`${this.baseUrl}/auth/getMedaillesByemail/${email}`);
  }
  getIdUserByEmail(email: string) : Observable<any> {
    return this.http.get(`${this.baseUrl}/auth/getIdUserByemail/${email}`);
  }
}
