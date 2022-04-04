import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ItemComponent } from './item/item.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { StopWatchComponent } from './stop-watch/stop-watch.component';
import { TodoComponent } from './todo/todo.component';

const routes: Routes = [
  {path:'todo',component:TodoComponent},
  {path:'login',component:LoginComponent},
  {path:'stopWatch',component:StopWatchComponent},
  {path:'register',component:RegisterComponent},
  {path:'todo/:id',component:ItemComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
