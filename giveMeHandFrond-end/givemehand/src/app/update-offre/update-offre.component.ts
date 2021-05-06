import { Component, OnInit } from '@angular/core';
import {Createoffer} from '../classes/createoffer';
import {Offre} from '../classes/offre';
import {OffreServiceService} from '../services/offre-service.service';
import {Router,ActivatedRoute} from '@angular/router';
import { HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-update-offre',
  templateUrl: './update-offre.component.html',
  styleUrls: ['./update-offre.component.scss']
})
export class UpdateOffreComponent implements OnInit {
    id:number;
    offres:Createoffer;
    submitted=false;
    categories : Observable< String[]>;
   
  constructor(private offreService: OffreServiceService,private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.submitted=false;
    this.categories = this.offreService.getCategories();
    this.offres= new Createoffer();
  
    this.id=this.route.snapshot.params['id']
    console.log(this.id);
    this.offreService.getofferid(this.id)
      .subscribe(data => {
        console.log(data)
        this.offres = data;
      }, error => console.log(error));
  }

  updateoffre() 
  {
    this.offres.id=this.id;
    this.offreService.updateoffer(this.offres)
      .subscribe(data => {
        console.log(data);
        this.offres = new Createoffer();
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.updateoffre();    
  }

  gotoList() {
    this.router.navigate(['/accueil']);
  }

}
