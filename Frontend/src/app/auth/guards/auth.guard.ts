import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

  constructor(
    private auth: AuthService,
    private router: Router
  ) {}

  canActivate(): boolean {
   /* if (!this.auth.isLoggedIn()) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
  */
    const token = this.auth.getToken();
    const role = localStorage.getItem('role');


    if(!token) {
      this.router.navigate(['/login']);
      return false;
    }

    if(role !== 'ADMIN') {
      this.router.navigate(['/login']);
      return false;
    }
  return true;
}
}