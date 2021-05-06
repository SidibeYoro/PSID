import { User } from "./user";

export class Createoffer {
    id: number;
    titre: string;
    description: string;
    cat: string;
    villeOffre: string;
    dateFinOffre: Date;
    dateOffre:Date;
    nbMedailles: number;
    moyennenote: number;
    
    motcle:string;
    active: boolean;
    user:User;
}
