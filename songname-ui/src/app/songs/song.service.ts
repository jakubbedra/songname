import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class SongService {

  constructor(private http: HttpClient) {
  }

  /**
   * jak zrobic ta checkliste jacy autorzy i utworzy maja byc w grze?
   * -lista z checkboxami, domyslnie wszystko zaznaczone
   * -odznaczamy to czego nie chcemy (lista tych ktore chcemy generowana jest przez
   * frondend)
   * -wysylamy liste uuid utworow i autorow z ktorych bedzie losowanie
   */
  addSong() {

  }

  addSongFile() {

  }

  fetchSong() {

  }

  fetchSongs() {

  }

  updateSong() {

  }

  updateSongFile() {

  }

  deleteSong() {

  }

}
