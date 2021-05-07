import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Demande } from "../classes/demande";
import { Offre } from "../classes/offre";


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
  
  
@Injectable({
  providedIn: 'root'
})

export class DemandeService {

    public url =environment.api_url;

    constructor(private http: HttpClient) { 
    }
    getMyRequestService(email: string): Observable<any> {
        return this.http.get(this.url+"/auth/mesdemandes/"+email);
    }
    getRequestService(sta:String,nbmedailles:number, date:String, idUser: number):Observable<any> {
        return this.http.get(this.url+"/getRequestfilter/"+sta+"/"+nbmedailles+"/"+date+"/"+idUser);
    }
    saveRequestService(demande:Demande, mail :string ,idOffre:number): Observable<any>
    {
     return this.http.post(this.url+"/addRequestService/"+mail+"/"+idOffre, demande,{ responseType: 'text'});
    }
    deleteServiceRequest(id:number):Observable<any> {
        return this.http.delete(this.url+"/deleteServiceRequest/"+id,{ responseType: 'text'});
    }
    getDemandeId(id: number) :Observable<any> {
        return this.http.get(this.url+"/getRequestServiceById/"+id);
    }
  
    getAllDemandesByOffer(idOffre : number): Observable<any>{
      return this.http.get(this.url+"/getDemandesByOffer/"+ idOffre);

    }
    getAllStatus() : Observable<any>{
      return this.http.get(this.url+"/getAllStatus");

    }
    updateDemande(demande: Demande, idDemande : number) : Observable<any>{
      return this.http.put(this.url+"/updateRequestService/"+idDemande,demande);
     
    }
    virtualMoney(idDemande : number): Observable<any>{
      return this.http.put(this.url+"/virtualMoney/"+ idDemande,{ responseType: 'text'});

    }
    isDemandCanUpdated(idDemande : number,newStatut : String ): Observable<any>{
      return this.http.get(this.url+"/isDemandCanUpdated/"+ idDemande +"/" + newStatut);

    }

    UpdateIsNoted(id_demande:number):Observable<any>
    {
      return this.http.put(this.url+"/updateisnoted/"+ id_demande,{ responseType: 'text'});

    }
    filterByStatut(statut: String ,idUser: number):Observable<any> {
      return this.http.get(this.url+"/filterByStatut/"+statut+"/"+idUser);
    }
    filterByStatutAndnbMedailles(statut: String, nbmedailles: number,idUser: number) :Observable<any> {
      return this.http.get(this.url+"/filterByStatutAndnbMedailles/"+statut+"/"+nbmedailles+"/"+idUser);
    }
    filterByStatutAndDate(statut: String, date: String, idUser: number) :Observable<any> {
      return this.http.get(this.url+"/filterByStatutAndDate/"+statut+"/"+date+"/"+idUser);
    }
    getNbDemandeByOffer() :Observable<any> {
      return this.http.get(this.url+"/getNbDemandeByOffer");
    }

}
