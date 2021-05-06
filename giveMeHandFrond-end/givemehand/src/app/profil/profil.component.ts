import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import { User } from '../classes/user';
import {UserService} from '../services/user.service';
import {Observable} from 'rxjs';
import { Offre } from '../classes/offre';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {
  profilId:number;
  user :User;
  offres:Observable<Offre[]>
  demandesAcceptes : number;
  demandesRefuses :number;
  demandesWaiting: number;


  constructor(private router: Router, private route: ActivatedRoute, private userService :  UserService) { }

  ngOnInit(): void {
    this.profilId=this.route.snapshot.params['id'];
    console.log(this.profilId);
 this.userService.getNbAcceptedDemande(this.profilId).subscribe(acceptes =>{

  console.log(acceptes)
  this.demandesAcceptes =acceptes;
  


}, error => console.log(error));

    this.userService.getNbRefusedDemande(this.profilId).subscribe(refuses =>{

      console.log(refuses )
     this.demandesRefuses =refuses ;
    
    }, error => console.log(error));

    this.userService.getNbWaitingDemande(this.profilId).subscribe(waiting =>{

      console.log(waiting)
     this.demandesWaiting =waiting ;
    
    }, error => console.log(error));

  // this.demandesTotales =this.demandesAcceptes + this.demandesRefuses+ this.demandesWaiting;
    // on recupere les offres de l'utilisateur
    this.userService.getOffersByUser(this.profilId).subscribe(offresUser =>{

      console.log(offresUser)
      this.offres =offresUser ;

    }, error => console.log(error));

    this.userService.getUserById(this.profilId).subscribe(dataUser =>{

      console.log(dataUser)
      this.user = dataUser;
     // this.user.dateInscription = datePipe.transform(dateInscription, 'yyyy-MM-dd');

    }, error => console.log(error));


  }

}
