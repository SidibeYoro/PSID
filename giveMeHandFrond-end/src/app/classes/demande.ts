import { Offre } from "./offre";
import { User } from "./user";
import { DatePipe, formatDate } from "@angular/common";

export class Demande {

    id: number;
    statut: string;
    dateDemande: string;
    nbMedailles: number;
    offre :Offre;
    titreOffre: string; 
    user : User;
    buttonDisabled : boolean;
    is_noted : boolean

    constructor(status : string,  date:string, offre: Offre, user:User, is_noted:boolean)
    {
        this.statut=status;
        this.dateDemande = date;
        this.offre =offre;
        this.user = user;
        this.is_noted=is_noted;

    }

}
