import { Routes } from '@angular/router';
import { ProductoListComponent } from './producto-list/producto-list.component';
import { ProductoFormComponent } from './producto-form/producto-form.component';

export const routes: Routes = [
  { path: 'productos', component: ProductoListComponent },
  { path: 'productoNuevo', component: ProductoFormComponent },
  { path: '', redirectTo: 'productos', pathMatch: 'full' } // Opción de redirección inicial
];
