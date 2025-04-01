import { Routes } from '@angular/router';
import { ListComponent } from './products/list/list.component';
import { FormComponent } from './products/form/form.component';


export const routes: Routes = [
{path: 'productos', component: ListComponent},
{path: 'productos/nuevo', component: FormComponent},
{path: '', redirectTo: 'productos', pathMatch: 'full'}
];
