import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {map} from "rxjs/operators";
import {Song} from "./song.model";
import {Observable} from "rxjs";

@Injectable()
export class SongsService {

  constructor(private http: HttpClient) {
  }

  /**
   * jak zrobic ta checkliste jacy autorzy i utworzy maja byc w grze?
   * -lista z checkboxami, domyslnie wszystko zaznaczone
   * -odznaczamy to czego nie chcemy (lista tych ktore chcemy generowana jest przez
   * frondend)
   * -wysylamy liste uuid utworow i autorow z ktorych bedzie losowanie
   */
  addSong(title: string, authorUuid: string, file: File) {
    const songData = new Blob([JSON.stringify({
      'title': title,
      'authorUuid': authorUuid
    })], {type: "application/json"});
    const formData = new FormData();
    formData.append('info', songData);
    formData.append('file', file);
    console.log(formData);
    return this.http.post(environment.apiURL + '/api/songs', formData, {observe: 'response'});
  }

  addSongFile() {

  }

  fetchSong() {

  }

  fetchSongs(): Observable<Song[]> {
    return this.http.get(environment.apiURL + '/api/songs')
      .pipe(map(responseData => {
        let songs: Song[] = [];
        for (const key in responseData) {
          if (responseData.hasOwnProperty(key)) {
            let data = {...responseData[key]};
            for (const key2 in responseData) {
              songs.push({...data[key2]});
            }
          }
        }
        return songs;
      }));
  }

  fetchAuthorSongs(uuid: string): Observable<Song[]> {
    return this.http.get<Song[]>(environment.apiURL + '/api/authors/' + uuid + '/songs')
      .pipe(map(responseData => {
        console.log(responseData);
        let songs: Song[] = [];
        for (const key in responseData) {
          if (responseData.hasOwnProperty(key)) {
            let data = {...responseData[key]};
            for (const key2 in data) {
              songs.push({...data[key2]});
            }
          }
        }
        console.log(songs);
        return songs;
      }));
  }

  updateSong() {

  }

  updateSongFile() {

  }

  deleteSong(uuid: string) {
    return this.http.delete(environment.apiURL + '/api/songs/' + uuid, {observe: 'response'});
  }

}
