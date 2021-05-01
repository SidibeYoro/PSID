import { DatePipe } from "@angular/common";
import { Adresse } from "./adresse";

export class User {

        id:number;
        email : string;
        password : string;
        firstName : string;
        lastName : string;
        phoneNumber : string;
        street: string;
        city : string;
        zip : string;
        country: string;
        dateInscription: String;
        medailles : number;
        adresse : Adresse


       /* constructor(   id:number,
            email : string,
            password : string,
            firstName : string,
            lastName : string,
            phoneNumber : string,
            street: string,
            city : string,
            zip : string,
            country: string,
            dateInscription: Date,datePipe:DatePipe ) {
                this.id=id;
                this.firstName=firstName;
                this.lastName = lastName;
            this.email=email;
            this.password=password;
            this.phoneNumber=phoneNumber;
            this.country=country;
            this.street=street;
            this.city=city;
            this.zip=zip;
            this.dateInscription = datePipe.transform(dateInscription, 'yyyy-MM-dd');

            
          } */
    
        /*constructor(username: string , email : String, motDePassse : String,
            github: String)
        {
            this.username=username;
            this.email=email;
            this.motDePasse=motDePassse;
            this.github=github;
        }  */

}
