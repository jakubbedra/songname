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
import {SongsService} from "./songs/songs.service";
import { SongAddComponent } from './songs/song-add/song-add.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AuthorListComponent,
    AuthorAddComponent,
    AuthorViewComponent,
    AuthorEditComponent,
    SongListComponent,
    SongAddComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    AuthorsService,
    SongsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
