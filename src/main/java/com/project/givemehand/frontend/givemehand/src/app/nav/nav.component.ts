import { Component, OnInit } from '@angular/core';
import { User } from '../classes/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {
   isLoggedIn =false;
   email : string;
   isLoggedInADM =false;
  // username : string 
   user :  any; 


  constructor(private userService: UserService) { }

  ngOnInit(): void {
     this.email = sessionStorage.getItem("currentUser")
     console.log("User connected : " +   this.email)
 
     this.userService.getUserByEmail(this.email).subscribe(data => {
         console.log("user data")
         console.log(data)
         this.user= data;
     });

if(this.email!=null){
      if(this.email==='admin@GMAH.com'){
           this.isLoggedInADM =true
      }
       else {  
          this.isLoggedIn =true

       }
     console.log(this.email);
     console.log("User simple" + this.isLoggedIn);
     console.log("ADMIN" + this.isLoggedInADM); 
  }

}
  logout() {
    window.sessionStorage.removeItem('currentUser');
    window.sessionStorage.removeItem('idUser');
    window.sessionStorage.clear();
    window.location.reload();
  }

   redirect() {
    location.href = "http://www.google.fr";
  
  }

}
