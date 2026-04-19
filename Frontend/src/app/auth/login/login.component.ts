import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { CommonModule } from '@angular/common'; 
import { LoginResponse } from '../auth/LoginResponse';
import { MatFormField, MatError, MatLabel } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule,
    MatFormField,
    MatInput,
    MatButtonModule,
    MatCardModule, MatIconModule, MatError, MatLabel],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  hidePassword = true;
  userForm: FormGroup;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(4)]]
    });
  }

  login() {
    if (this.userForm.invalid) return;    
    this.authService.login(this.userForm.value).subscribe({
      next: (response: LoginResponse) => {
        
        if (response.role === 'ADMIN') {
          this.router.navigate(['/AdminDashboard']);
        } else if (response.role === 'WORKER') {
          this.router.navigate(['/WorkerDashboard']);
        } else {
          this.router.navigate(['/MagasinerDashboard']);
        }

      },
      error: () => {
        this.errorMessage = 'User name or password invalid';
      }
    });
  }

  
}