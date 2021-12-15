import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {AuthorListComponent} from './authors/author-list/author-list.component';
import {AuthorAddComponent} from './authors/author-add/author-add.component';
import {AuthorsService} from "./authors/authors.service";
import {HttpClientModule} from "@angular/common/http";
import { AuthorViewComponent } from './authors/author-view/author-view.component';
import { AuthorEditComponent } from './authors/author-edit/author-edit.component';
import { SongListComponent } from './songs/song-list/song-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AuthorListComponent,
    AuthorAddComponent,
    AuthorViewComponent,
    AuthorEditComponent,
    SongListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [AuthorsService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
