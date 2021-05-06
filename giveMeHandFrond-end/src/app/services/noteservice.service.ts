import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from "../../environments/environment";
import { Observable } from 'rxjs';
import { Offre } from '../classes/offre';
import { Createoffer } from '../classes/createoffer';
import { Filtre } from '../classes/filtre';
import {Note} from '../classes/note';

@Injectable({
  providedIn: 'root'
})
export class NoteserviceService {

  public url =environment.api_url;

  constructor(private http: HttpClient) { 

  }
  //Give rate
  saveNote(note:number, id:number):Observable<any> 
  {
    console.log(this.url+"/SaveNote/"+note+"/"+id);
    return this.http.put(this.url+"/SaveNote/"+note+"/"+id,{ responseType: 'text'});
  }

}
