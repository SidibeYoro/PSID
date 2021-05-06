import { Component, OnInit } from '@angular/core';
import {Createoffer} from '../classes/createoffer';
import {Demande} from '../classes/demande';

import {OffreServiceService} from '../services/offre-service.service';
import {DemandeService} from '../services/demande-service';

import {Router,ActivatedRoute} from '@angular/router';
import { HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-offre-detail',
  templateUrl: './offre-detail.component.html',
  styleUrls: ['./offre-detail.component.scss']
})
export class OffreDetailComponent implements OnInit {
 id:number;
 offres:Createoffer;
 demandes : Demande[];
status : Observable< String[]>;
isUpdatedDemande =false;
ancienStatutDemande : string[];
s : string;
selectedDay: string = '';


constructor(private offreService: OffreServiceService,private demandeService : DemandeService,private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    
    this.offres= new Createoffer();
    this.id=this.route.snapshot.params['id'];
    this.offreService.getofferid(this.id).subscribe(data => {
      console.log(data)
      this.offres = data;
    }, error => console.log(error));

    this.status= this.demandeService.getAllStatus();

    this.demandeService.getAllDemandesByOffer(this.id).subscribe(dataDemandes=> {
     console.log("Ancien data", dataDemandes)
      this.demandes= dataDemandes;


    }, error => console.log(error));
}
list(){
  this.router.navigate(['/offrelist']);
}
listDemande(idOffre : number){
  this.router.navigate(['/details/'+ idOffre ]);

}
goProfilDetails(idUser : number){
  this.router.navigate(['/profil/'+ idUser ]);

}

updateDemande(demande: Demande, idDemande : number){
  console.log(demande);
  console.log(idDemande);

  this.demandeService.updateDemande(demande,idDemande)    
  .subscribe(data => {
    this.isUpdatedDemande = true;
      this.demandeService.virtualMoney(idDemande).subscribe(dataVM => {
      console.log("Data" + dataVM);
     // this.listDemande(this.id);

     }, error => console.log(error)); 
    console.log(data);
 //   this.offres = new Createoffer();
    //this.list();
  }, error => console.log(error));


}
 //event handler for the select element's change event
 selectChangeHandler () {

this.demandes.forEach(element => {
  console.log("Demande id" + element.id + "Nouveau statut",element.statut);

this.demandeService.isDemandCanUpdated(element.id,element.statut).subscribe(isUpdatable => {
  element.buttonDisabled = isUpdatable;
  console.log("IS UPADATED",isUpdatable);
}, error => console.log(error));
}); 

}


}




