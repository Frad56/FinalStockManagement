import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {


  private auth= inject(AuthService);
  private router= inject(Router);
  
  canActivate(route: ActivatedRouteSnapshot): boolean {

    const token = this.auth.getToken();

    if (!token) {
      this.router.navigate(['/login']);
      return false;
    }
    console.log('****** INTERCEPTOR EXÉCUTÉ ******');

    console.log("TOKEN:", this.auth.getToken());
    console.log("ROLE:", localStorage.getItem('role'));
    const expectedRole = route.data['role'];
    const userRole = localStorage.getItem('role');
    

    if (expectedRole && !expectedRole.includes(userRole)) {
      this.router.navigate(['/login']);
      return false;
    }

    return true;
  }

}