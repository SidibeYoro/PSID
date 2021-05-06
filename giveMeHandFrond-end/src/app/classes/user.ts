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
        adresse : Adresse;


}
