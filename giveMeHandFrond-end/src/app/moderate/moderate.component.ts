import { Component, OnInit } from '@angular/core';
import {OffreServiceService} from '../services/offre-service.service';
import { UserService } from '../services/user.service';

import {Router,ActivatedRoute} from '@angular/router';
import { HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Offre} from '../classes/offre';
import { User } from '../classes/user';

@Component({
  selector: 'app-moderate',
  templateUrl: './moderate.component.html',
  styleUrls: ['./moderate.component.scss']
})
export class ModerateComponent implements OnInit {
  offres:Observable<Offre[]>
  users:Observable<User[]>;

  empty =false;
  categories : Observable< String[]>;
  constructor(private offreService: OffreServiceService,private userService: UserService,private router: Router) { }

  ngOnInit(): void {
    this.reloadData();
    this.reloadDataUser();

  }


reloadData() {
  this.offres = this.offreService.getOffreList();

  this.offres.subscribe((value) => {
    console.log(value);
    if(value.length == 0) {
      this.empty = true;
    }
  }, (error) => {
    console.log(error);
  }, () => {
    console.log('Fini !');
  });
}
reloadDataUser()
{

  this.users = this.userService.getUserList();

  this.users.subscribe((value) => {
    console.log(value);
    if(value.length == 0) {
      this.empty = true;
    }
  }, (error) => {
    console.log(error);
  }, () => {
    console.log('Fini !');
  });

}
deleteoffer(id: number) {
  console.log("id"+id);
  this.offreService.deleteoffer(id).subscribe(
      data => {
        console.log(data);
        this.reloadData();
        this.reloadDataUser();

      },
      error => console.log(error));
      this.reloadData();
}
deleteuser(id: number) {
  console.log("id"+id);
  this.userService.deleteUser(id).subscribe(
      data => {
        console.log(data);
        this.reloadDataUser();
        this.reloadData();

      },
      error => console.log(error));
      this.reloadDataUser();
      this.reloadData();

}

}
