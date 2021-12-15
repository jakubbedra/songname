import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthorListComponent} from "./authors/author-list/author-list.component";
import {AuthorEditComponent} from "./authors/author-edit/author-edit.component";
import {AuthorViewComponent} from "./authors/author-view/author-view.component";

const routes: Routes = [
  {path: '', component: AuthorListComponent},
  {path: 'authors', component: AuthorListComponent},
  {path: 'authors/edit/:uuid', component: AuthorEditComponent},
  {path: 'authors/:uuid', component: AuthorViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
