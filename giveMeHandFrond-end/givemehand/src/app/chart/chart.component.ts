import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DemandeService } from '../services/demande-service';



@Component({
  selector: 'app-chart',
  template: `<fusioncharts
              width="100%" 
              height="450"
              type="stackedcolumn2d"
              dataFormat="json"
              [dataSource]=dataSource >
          </fusioncharts>`,
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit {

  dataSource : any;

  constructor(private router: Router, private route: ActivatedRoute, private demandeService :  DemandeService) { 
    
  }


  ngOnInit(): void {
    this.demandeService.getNbDemandeByOffer().subscribe(data =>{
      console.log(data);
      this.dataSource = {
        chart: {
          data
        }
      };
    }, error => console.log(error));
   
  }

}
