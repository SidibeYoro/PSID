import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AgmCoreModule } from '@agm/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AccueilComponent } from './accueil/accueil.component';
import { MesDemandesComponent } from './mes-demandes/mes-demandes.component';
import { LoginComponent } from './login/login.component';
import { NavComponent } from './nav/nav.component';
import {HttpClientModule} from '@angular/common/http';
import { ChartsModule } from 'ng2-charts';

import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';
import { UpdateProfilComponent } from './update-profil/update-profil.component';
import { CreateOffreComponent } from './create-offre/create-offre.component';
import { OffreListComponent } from './offre-list/offre-list.component';
import { OffreDetailComponent } from './offre-detail/offre-detail.component';
import { UpdateOffreComponent } from './update-offre/update-offre.component';
import { ModerateComponent } from './moderate/moderate.component';
import { ProfilComponent } from './profil/profil.component';
import { ChartComponent } from './chart/chart.component';

// Import angular2-fusioncharts
import { FusionChartsModule } from 'angular2-fusioncharts';
// Import FusionCharts library
import * as FusionCharts from 'fusioncharts';
// Import FusionCharts Charts module
import * as Charts from 'fusioncharts/fusioncharts.charts';





@NgModule({
  declarations: [
    AppComponent,
    AccueilComponent,
    MesDemandesComponent,
    LoginComponent,
    NavComponent,
    NavComponent,
    RegisterComponent,
    UpdateProfilComponent,
    NavComponent,
    CreateOffreComponent,
    OffreListComponent,
    OffreDetailComponent,
    UpdateOffreComponent,
    ModerateComponent,
    ProfilComponent,
    ChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    ChartsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDmR2n_yrz5nIA_hPK8h6ctKuX4uVyDb68',
      libraries: ['places']
    })
  ],
  providers: [
    HttpClientModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

