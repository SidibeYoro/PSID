import { Component, ElementRef, Input, OnInit, ViewChild,NgZone} from '@angular/core';
import { AuthService } from '../services/auth.service';
import { User } from '../classes/user';
import { MapsAPILoader, MouseEvent } from '@agm/core';
import {Router,ActivatedRoute} from '@angular/router';
import { HttpClient} from '@angular/common/http';
import {Filtre} from '../classes/filtre'
import {Offre} from '../classes/offre';
import {Observable} from 'rxjs';

//import {} from '@googlemaps';
import { FormControl } from '@angular/forms';

//import { Filtre } from '../classes/filtre';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  user : User = new User();;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  lat: number = 48.856614;
  lng: number = 2.3522219;
  zoom:number = 50;
  address: string = "";
  private geoCoder;
  street : string; 
	postCode: string; 
	 city: string=""; 
	country: string="";

	@ViewChild('search' ) 
  public searchElementRef: ElementRef;


  

  constructor(private authService: AuthService,private mapsAPILoader: MapsAPILoader,private router: Router,private http: HttpClient,private route:ActivatedRoute,private ngZone: NgZone ) { }

  ngOnInit() {

   // console.log(this.city);
    this.mapsAPILoader.load().then(() => {
     // this.setCurrentLocation();
      this.geoCoder = new google.maps.Geocoder;

      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement);
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          //get the place result
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();
          console.log(place);
          this.address = place.formatted_address;
          this.street  = place.address_components[0].long_name + " " +place.address_components[1].long_name;
          this.city= place.address_components[2].long_name;
          this.postCode =place.address_components[6].long_name;
          this.country = place.address_components[5].long_name;

          this.user.city=this.city;
          this.user.country=this.country;
          this.user.zip=this.postCode;
          this.user.street=this.street;
          console.log(this.street);
          console.log(this.country);
          console.log(this.postCode);
          console.log(this.city);
          var latitude = place

          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }
          
        });
      });
    }); 
  }
  
  

  onSubmit() {
    this.authService.register(this.user).subscribe(
      data => {
        console.log(this.user);
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  /* 
  ** Récupérer la position actuelle 
  */
  private setCurrentLocation() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
        this.zoom = 15;
      });
    }
  }


  

  

}
