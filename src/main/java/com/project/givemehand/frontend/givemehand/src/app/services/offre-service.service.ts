import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from "../../environments/environment";
import { Observable } from 'rxjs';
import { Offre } from '../classes/offre';
import { Createoffer } from '../classes/createoffer';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class OffreServiceService{
  public url =environment.api_url;

  constructor(private http: HttpClient) { 

  }


  getOffreList(): Observable<any> {
    return this.http.get(this.url+'/getAllOffer');
  }
  getofferfilter(categorie:String, ville:String, nbmedailles:number, motcle:String, date:String):Observable<any> {
  return this.http.get(this.url+"/getofferfilter/"+categorie+"/"+ville+"/"+nbmedailles+"/"+motcle+"/"+date);
  }

  getCategories(): Observable<any> {
    return this.http.get(this.url+'/getAllCat');
  }
  deleteoffer(id:number):Observable<any> {
    return this.http.delete(this.url+"/DeleteOffer/"+id,{ responseType: 'text'});
  }
  saveoffer(offres:Createoffer)
  {
     return this.http.post(this.url+'/CreateOffer', offres, { responseType: 'text'});
  }
 updateoffer(value: Createoffer):Observable<any> {
  return this.http.put(this.url+"/UpdateOffer/",value,{ responseType: 'text'});
} 
getofferid(id:number):Observable<any>
{
  return this.http.get(this.url+"/findById/"+ id)
}


}