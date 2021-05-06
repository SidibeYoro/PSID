import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { CreateOffreComponent } from './create-offre/create-offre.component';
import { MesDemandesComponent } from './mes-demandes/mes-demandes.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { UpdateProfilComponent } from './update-profil/update-profil.component';
import { ModerateComponent } from './moderate/moderate.component';
import { OffreDetailComponent } from './offre-detail/offre-detail.component';
import { OffreListComponent } from './offre-list/offre-list.component';
import { UpdateOffreComponent } from './update-offre/update-offre.component';
import { ProfilComponent } from './profil/profil.component';
import { ChartComponent } from './chart/chart.component';

const routes: Routes = [
  { path: '', component: AccueilComponent },
  { path: 'accueil', component: AccueilComponent },
  { path: 'mesDemandes', component: MesDemandesComponent},
  //{ path: 'monProfil', component: MonProfilComponent },
  { path: 'profil/:id', component: ProfilComponent },

  { path: 'inscription', component: RegisterComponent },
  { path: 'connexion', component: LoginComponent },
  { path: 'updateprofil', component:  UpdateProfilComponent },

  { path: 'accueil/:id', component: AccueilComponent },
  { path: 'details/:id', component: OffreDetailComponent},
  { path: 'add', component:CreateOffreComponent},
  { path: 'update/:id', component: UpdateOffreComponent },
  { path: 'offrelist', component: OffreListComponent },
  { path: 'moderate', component: ModerateComponent },
  { path: 'chart', component: ChartComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
