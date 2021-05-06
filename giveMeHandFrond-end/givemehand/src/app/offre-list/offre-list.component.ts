import { Component, OnInit } from '@angular/core';
import {Createoffer} from '../classes/createoffer';
import {OffreServiceService} from '../services/offre-service.service';
import {Router,ActivatedRoute} from '@angular/router';
import { HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-offre-list',
  templateUrl: './offre-list.component.html',
  styleUrls: ['./offre-list.component.scss']
})
export class OffreListComponent implements OnInit {
 empty =false;
 offres:Observable<Createoffer[]>
 categories : Observable< String[]>;
 id:number;
 id_user:number;
 email:string;
  constructor(private offreService: OffreServiceService,private router: Router, private route: ActivatedRoute)
  { }

  ngOnInit()
  {
    this.email= sessionStorage.getItem('currentUser');
    this.offreService.getIdByEmail(this.email).subscribe(
    data=>{console.log(this.id_user=data)})
    
  this.reloadData();
  }

  reloadData()
{
  
  console.log("votre email est:" + this.email)
  this.offres=this.offreService.getOfferByEmail(this.email)
}
deleteoffer(id: number)
{
  console.log("id"+id);
  this.offreService.deleteoffer(id).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
      this.reloadData();
}
offreDetails(id: number){
  this.router.navigate(['details', id]);
}

}
