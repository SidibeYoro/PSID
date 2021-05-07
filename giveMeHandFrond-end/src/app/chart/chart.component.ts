import { Component, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label, MultiDataSet } from 'ng2-charts';
import { DemandeService } from '../services/demande-service';
import { OffreServiceService } from '../services/offre-service.service';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit {

  barChartLabels: Label[] = [];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];
  offres: any;
  categories: any;
  villes: any;
  barChartData: ChartDataSets[];
  barChartDataDemandes: ChartDataSets[];
  colors = [{borderColor: 'black',backgroundColor: [
    "#E7930F",
    "#FFFCFC",
    "#D4B584 "
]}];

  doughnutChartLabels: Label[] = ['En attente', 'Acceptée', 'Refusée'];
  doughnutChartData: MultiDataSet = [];
  doughnutChartType: ChartType = 'doughnut';

  constructor(private offreService: OffreServiceService,private demandeService: DemandeService){

  }
  
  lbarChartOptions: ChartOptions = {
    responsive: true,
  };

  ngOnInit(): void {
    this.refreshData();
    
  }

  refreshData() {
    var data = [];
    var data2 = [];
    this.offres = this.offreService.getOffreList();
    
    this.categories = this.offreService.getCategories();
    this.categories.subscribe((value) => {
      if(value.length == 0) {
        console.log("aucune catégorie");
      }
      else{
        var i = 1;
        console.log(value.length);
          value.forEach(element =>{
              if(i<value.length){
                this.barChartLabels.push(value[i]);
                data.push(0);
                data2.push(0);
              }
          i++;
        });
      }
    });

    

    var nbCat = this.barChartLabels.length;
    this.offres.subscribe((value) => {
      if(value.length == 0) {
        console.log("aucune offre");
      }
      else{
        value.forEach(element => {
          var cat = element.categorie;
          var j =  this.barChartLabels.indexOf(cat);
          data[j] =data[j]+1;
        });
      }});

      this.barChartData = [{ data: data, label: 'Offres/Catégories',backgroundColor:'rgba(229, 152, 102)' }];

      var nbOffres= [0,0,0];
      var demandes = this.demandeService.getAllDemandes();
      demandes.subscribe((value) =>{
        if(value.length == 0) {
          console.log("aucune demande");
          //this.barChartDataDemandes = [{ data: data2, label: 'Demandes/Catégorie',backgroundColor:'rgba(243, 156, 18 )' }];
        }
        else{
          value.forEach(element => {
            var status = element.statut;
            var cat = element.offre.categorie;
            var j =  this.barChartLabels.indexOf(cat);
            data2[j] =data2[j]+1;
            
            if(status=="ATTENTE"){
              nbOffres[0] = nbOffres[0]+1;
            }
            else if(status == "ACCEPTE"){
              nbOffres[1] = nbOffres[1]+1;
            }
            else{
              nbOffres[2] = nbOffres[2]+1;
            }
          });
        }
        this.barChartDataDemandes = [{ data: data2, label: 'Demandes/Catégorie',backgroundColor:'rgba(243, 156, 18 )' }];
        this.doughnutChartData= [nbOffres];
      });
      
    }

}
