import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SigupComponent } from './sigup/sigup.component';
import { AdminComponent } from '../features/users/admin/pages/admin.component'; 
import { WorkerComponent } from '../features/users/worker/pages/worker.component';
import { MagasinerDashboardComponent } from '../features/users/magasiner/pages/magasiner-dashboard/magasiner-dashboard.component';
import { AuthGuard } from './guards/auth.guard';
export const authRoutes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: SigupComponent, canActivate: [AuthGuard]},
    { path: 'AdminDashboard', component: AdminComponent ,canActivate: [AuthGuard]}, 
    {path:'SignUp', component: SigupComponent},
    { path: 'WorkerDashboard', component: WorkerComponent },
    { path: 'MagasinerDashboard', component: MagasinerDashboardComponent }, 
];
