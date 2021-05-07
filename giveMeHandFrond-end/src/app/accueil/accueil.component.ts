import { Adresse } from '../classes/adresse';
import { MapsAPILoader, MouseEvent } from '@agm/core';
import { Component, ElementRef, OnInit, ViewChild,NgZone } from '@angular/core';
import {Offre} from '../classes/offre';
import {OffreServiceService} from '../services/offre-service.service';
import {Router,ActivatedRoute} from '@angular/router';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Filtre } from '../classes/filtre';
import { DemandeService } from '../services/demande-service';
import { Demande } from '../classes/demande';
import { UserService } from '../services/user.service';
import { User } from '../classes/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent implements OnInit {
    isPossible: boolean =true;
    private geoCoder;
    zoom:number = 50;
  empty =false;
  offres: Observable<Offre[]>;
  categories : Observable< String[]>;
  lat: number = 48.856614;
  lng: number = 2.3522219;
  address: string = "";
  filtre: Filtre;
    addresses: Adresse[] = new Array();
  markers: marker[] = [];
  medailles: number;
  user : any;
  email: string;
  isLoggedIn =false;



  @ViewChild('search')
  public searchElementRef: ElementRef;
  constructor(private offreService: OffreServiceService,private demandeService: DemandeService,private userService: UserService,private router: Router,private http: HttpClient,private route:ActivatedRoute, private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone) {
    }
  ngOnInit(): void {
    this.user = new User();
    this.filtre = new Filtre();
    this.categories = this.offreService.getCategories();
    this.email = sessionStorage.getItem("currentUser");
    this.userService.getUserByEmail(this.email)
    .subscribe(data => {
      this.user= data;
    });
    if(this.email!=null){
      this.isLoggedIn =true;
    } else {  
      this.isLoggedIn =false;
      console.log(this.email);
      console.log("User simple" + this.isLoggedIn);
    }
  
    this.userService.getMedaillesUserByEmail(this.email).subscribe(
      data=>{
       this.medailles = data;
      }
    );

    //*Google Maps

   // Utilisation de l'api google maps
   this.mapsAPILoader.load().then(() => {
    //Récupération de la position actuelle :
   this.setCurrentLocation();

   //autocomplétion du champs addresse du filtre
   let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement);
   autocomplete.addListener("place_changed", () => {
     this.ngZone.run(() => {
       //Obtenir la place
       let place: google.maps.places.PlaceResult = autocomplete.getPlace();
       this.address = place.formatted_address;
       //Vérifier le résultats
       if (place.geometry === undefined || place.geometry === null) {
         return;
       }

       //récupérer la latitude et longitude
       this.lat = place.geometry.location.lat();
       this.lng = place.geometry.location.lng();
       });
     });
   });

   this.markers.push({
     lat: this.lat,
     lng: this.lng,
     draggable: true,
     label: 'Domicile'
 })
 // Récupération de la liste des offres :
   this.reloadData();
  }

  reloadData() {
    this.offres = this.offreService.getOffreList();
    this.offres.subscribe((value) => {
      if(value.length == 0) {
        this.empty = true;
      }
      else {
        var i = 0;
        // Affichage des offres sur la carte
        value.forEach(element =>{
          this.addresses[i] = element.user.adresse;
          let add: string = this.addresses[i].street+ ", " + this.addresses[i].zip + " " +this.addresses[i].city + ", "+ this.addresses[i].country;
          this.mapsAPILoader.load().then(() => {
            this.setCurrentLocation();
            this.geoCoder = new google.maps.Geocoder;
            this.codeAddress(add, this.markers);
          });

          i++;
        })
      }
    }, (error) => {
      console.log(error);
    }, () => {
      console.log('Fini !');
    });
  }
  
  filtrer() {
    if(this.address !=""){
      this.filtre.ville = this.address;
    }
    this.offres = this.offreService.filtrer(this.filtre);
    this.address ="";
    this.markers =[];
    // Affichage des offres sur la carte
    this.offres.subscribe((value) => {
      if(value.length == 0) {
        this.empty = true;
      }
      else{
        var i = 0;
        value.forEach(element =>{
        this.addresses[i] = element.user.adresse;
        let add: string = this.addresses[i].street+ ", " + this.addresses[i].zip + " " +this.addresses[i].city + ", "+ this.addresses[i].country;
        this.mapsAPILoader.load().then(() => {
          this.codeAddress(add, this.markers);
        });
        i++;
      })
    }
    });
  }

  createDemande(offre: Offre) {
    if (this.isLoggedIn) {
      let ladate=new Date();
      let demande:Demande;
      //this.user = this.userService.getUserByEmail(email);
      //verifier si la date de la demande est inferieur à la date de l'offre
      let diff :number = this.medailles - offre.nbMedailles;
      console.log("diff",diff);
      if( diff >= 0 ) {
        demande = new Demande ('ATTENTE',  this.formatDate(ladate), offre, this.user,false);
        this.isPossible =true;
        console.log("Demande",demande);
        this.demandeService.saveRequestService(demande,this.email,offre.id).subscribe(data => {
          console.log(data)

        },);
        this.router.navigate(['mesDemandes'])
          .then(() => {
              window.location.reload();
        });
        console.log("Possible de faire la demande !");
      } else {
        this.isPossible =false;
      }
    } else {
      this.router.navigate(['connexion']);
    }
  }

 /*
 ** Modifier le format de la date
 */
 formatDate(date :Date) : string {
  let month = ''+(date.getMonth() + 1);
  let day = ''+date.getDate();
  let  year = date.getFullYear();
if (month.length < 2)
    month = '0' + month;
if (day.length < 2)
    day = '0' + day;
return [year,month,day].join('-');
}

/****************API GOOGLE MAPS  ****************************/


/*
** Récupérer la position actuelle
*/

private setCurrentLocation() {
if ('geolocation' in navigator) {
  navigator.geolocation.getCurrentPosition((position) => {
    this.lat = position.coords.latitude;
    this.lng = position.coords.longitude;
    this.zoom = 10;
  });
}
}

/*
** Déplacement du marker
*/

markerDragEnd($event: MouseEvent) {
console.log($event);
this.lat = $event.coords.lat;
this.lng = $event.coords.lng;
this.getAddress(this.lat, this.lng);
}


/*
** Récupération d'une addresse
*/

getAddress(latitude, longitude) {
this.geoCoder.geocode({ 'location': { lat: latitude, lng: longitude } }, (results, status) => {
  console.log(results);
  console.log(status);
  if (status === 'OK') {
    if (results[0]) {
      this.zoom = 10;
      this.address = results[0].formatted_address;
    } else {
      window.alert('No results found');
    }
  } else {
    window.alert('Geocoder failed due to: ' + status);
  }

});
}

/*
** Ajout de markers sur la carte
*/

codeAddress(address, markers) {
var mapOptions = {
  zoom : 10
}
this.geoCoder.geocode( { 'address': address}, function(results, status) {
      if (status == 'OK') {
        console.log(results[0]);
        markers.push({
          lat : results[0].geometry.location.lat(),
          lng : results[0].geometry.location.lng(),
          draggable: false,
          label:''
        })
      }
      else if (status == google.maps.GeocoderStatus.OVER_QUERY_LIMIT) {
        setTimeout(function () {
            //Recursively calling spotPosition method for lost addresses
            this.codeAddress(address);
        }, 2000);
    }
      else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });

}
}

interface marker {
lat: number;
lng: number;
draggable: boolean;
label: string;

}


