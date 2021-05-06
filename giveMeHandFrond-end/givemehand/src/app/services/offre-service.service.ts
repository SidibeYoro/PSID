import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from "../../environments/environment";
import { Observable } from 'rxjs';
import { Offre } from '../classes/offre';
import { Createoffer } from '../classes/createoffer';
import { Filtre } from '../classes/filtre';

const httpOptions = {
 // headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class OffreServiceService{
 
  public url =environment.api_url;

  constructor(private http: HttpClient) { 

  }

  //Obtenir toutes les offres
  getOffreList(): Observable<any> {
    return this.http.get(this.url+'/getAllOffer',{ responseType: 'json'});
  }
 
 //obtenir toutes les offres en fonction d'un utilisateur
  getOfferByUser(id_user: number):any{
    return this.http.get(this.url+"/mesoffres/"+id_user, { responseType: 'text'});
}

//obtenir toutes les offres en fonction de l'email utilisateur
getOfferByEmail(email: string):Observable<any>{
  return this.http.get(this.url+"/test/"+email);
}

getIdByEmail(email: String): Observable<any> {
  return this.http.get(this.url+"/IdByEmail/"+email);
}
  getofferfilter(categorie:String, ville:String, nbmedailles:number, motcle:String, date:String):Observable<any> {
  return this.http.get(this.url+"/getofferfilter/"+categorie+"/"+ville+"/"+nbmedailles+"/"+motcle+"/"+date);
  }
  
  getCategories(): Observable<any> {
    return this.http.get(this.url+'/getAllCat',{ responseType: 'json'});
  }
  deleteoffer(id:number):Observable<any> {
    return this.http.delete(this.url+"/DeleteOffer/"+id,{ responseType: 'text'});
  }

  saveoffer(offres:Createoffer,id:number)
  {
     return this.http.post(this.url+"/CreateOffer/"+id, offres, { responseType: 'text'});
  }
 updateoffer(value: Createoffer):Observable<any> {
  return this.http.put(this.url+"/UpdateOffer/",value,{ responseType: 'text'});
} 
getofferid(id:number):Observable<any>
{
  return this.http.get(this.url+"/findById/"+ id)
}
getmoyenne(id:number):Observable<any>
{
  return this.http.get(this.url+"/moyennenote/"+ id,{ responseType: 'text'})

}
updatemoyenne(id_offre:number,moyenne:number)
{
  return this.http.put(this.url+"/Updatemoyenne/"+id_offre+"/"+moyenne,{ responseType: 'text'})
}

filtrer(filtre: Filtre):Observable<any>
{
    let date;
    let medailles;
    if(filtre.categorie == undefined){
      filtre.categorie = "TOUTES";
    }
    if(filtre.dateFiltre == undefined){
      date = "01011000"
    }
    else{
      let dates  = filtre.dateFiltre.split('-');
      date = dates[0]+ dates[1]+dates[2];
    }
    if(filtre.nbMedailles == undefined || filtre.nbMedailles == "" ){
      medailles = "-1";
    }
    else{
      medailles = filtre.nbMedailles;
    }
    if(filtre.ville == undefined){
      filtre.ville = "";
    }
    else{
      let v = filtre.ville.split(",");
      filtre.ville = v[0];
      
    }
    console.log(filtre.ville);
    if(filtre.motCle == undefined){
      filtre.motCle = "";
    }

    let httpParams = new HttpParams()
                        .set('categorie', filtre.categorie)
                      .set('ville', filtre.ville)
                      .set('nbMedailles', medailles)
                      .set('motcle', filtre.motCle)
                      .set('date', date);
                    
    return this.http.get<Offre>(this.url+"/filterOffers", {params: httpParams, responseType: 'json'});

  }
}
