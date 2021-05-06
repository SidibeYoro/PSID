import { Component, OnInit } from '@angular/core';

@Component({
  //selector: 'app-give-note',
  selector: 'ngbd-rating-basic',
  templateUrl: './give-note.component.html',
  styleUrls: ['./give-note.component.scss']
})
export class GiveNoteComponent implements OnInit {
  currentRate =0;
  submitted=false;

  
  constructor() { }

  ngOnInit(): void {

   // console.log("test", this.currentRate)
}


save(currentRate){
  //rate = this.currentRate;
  console.log("votre note est ",currentRate);
}

onSubmit() {
 this.submitted = true;
  this.save(this.currentRate);    
}


}

