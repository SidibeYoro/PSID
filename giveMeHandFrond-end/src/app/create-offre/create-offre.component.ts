import { Component, OnInit } from '@angular/core';
import {Createoffer} from '../classes/createoffer';
import {OffreServiceService} from '../services/offre-service.service';
import {Router,ActivatedRoute} from '@angular/router';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Component({
  selector: 'app-create-offre',
  templateUrl: './create-offre.component.html',
  styleUrls: ['./create-offre.component.scss']
})
export class CreateOffreComponent implements OnInit {
  
 offres:Createoffer = new Createoffer();
  empty =false;
  submitted=false;
  categories : Observable< String[]>;
  email:string;
  id_user:number;
    detectBadClassTitre : boolean=false;;
  detectBadClassDesc: boolean=false;;
   constructor(private offreService: OffreServiceService,private router: Router,private http: HttpClient) 
   { } 
  
  ngOnInit(): void {
    this.categories = this.offreService.getCategories();
    this.email= sessionStorage.getItem('currentUser')
    this.offreService.getIdByEmail(this.email).subscribe(
      data=>{console.log(this.id_user=data)
      }
    )
      console.log("test", this.id_user);
  }
  
  newOffre():void
  {
   
    this.submitted=false;
    this.offres= new Createoffer();
  }

  save()
  { 
    console.log("creation d'offres");
    this.offreService.saveoffer(this.offres,this.id_user).subscribe(data => {
      console.log(this.offres)
      console.log(data)
      this.offres = new Createoffer();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['accueil']);
  }
  
   detectTitre(text : string ){
    if (text!=''){


      this.offreService.detectNegativeClassForText(text).subscribe(data=> {
        if (data=="true"){
          this.detectBadClassTitre=true;
          console.log("class" + this.detectBadClassTitre);


        }
        else {
          this.detectBadClassTitre=false;
          console.log("class" + this.detectBadClassTitre);


        }
        console.log("detect");
        console.log("Resultat classification", data);

      }); 
    }

  }
detectDesc(text : string ){
    if (text!=''){
    
          this.offreService.detectNegativeClassForText(text).subscribe(data=> {
            if (data=="true"){
              this.detectBadClassDesc=true;
              console.log("class" + this.detectBadClassDesc);
    
    
            }
            else {
              this.detectBadClassDesc=false;
              console.log("class" + this.detectBadClassDesc);
    
    
            }
            console.log("detect");
            console.log("Resultat classification", data);
    
       }); 
  }
      }
  
  
  

}
