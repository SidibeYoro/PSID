import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { User } from '../classes/user';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService,private router: Router,) { }

  ngOnInit(): void {

  }
  user: User = new User();
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';


  onSubmit() {
    this.authService.login(this.user).subscribe(
      data => {
        console.log(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        sessionStorage.setItem('currentUser', this.user.email);

        this.router.navigate(['accueil'])
        .then(() => {
          window.location.reload();
        });
        
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }

}
