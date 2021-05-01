import { Component, OnInit } from '@angular/core';
import { User } from '../classes/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-update-profil',
  templateUrl: './update-profil.component.html',
  styleUrls: ['./update-profil.component.scss']
})

export class UpdateProfilComponent implements OnInit {
  user :  any; 
  semail : string;
 submitted=false;
Boolean = false;s
errorMessage = '';


  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.user = new User();
    this.semail = sessionStorage.getItem("currentUser")
    console.log("User connected : " +   this.semail)

    this.userService.getUserByEmail(this.semail)
      .subscribe(data => {
        console.log("user data")
        console.log(data)
        this.user= data;

      },
      err => {
        this.errorMessage = err.error.message;
       
      }
    );
  }
  onSubmit(){
    this.submitted = true;
    console.log("Data USER SUBMITED")
    console.log("USER STREET" + this.user.adresse.street)
    this.userService.updateUser(this.user).subscribe(
      data=> console.log(data),
      error => console.log(error)
    );
  }

  }
  

  /* */


