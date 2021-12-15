import {Author} from "./author.model";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {map} from "rxjs/operators";
import {Observable} from "rxjs";

@Injectable()
export class AuthorsService {

  constructor(private http: HttpClient) {
  }

  addAuthor(name: string): Observable<any> {
    const authorData = {name: name};
    return this.http.post(environment.apiURL + '/api/authors', authorData, {observe: 'response'});
  }

  fetchAuthor(uuid: string): Observable<Author> {
    return this.http.get<Author>(environment.apiURL + '/api/authors/' + uuid)
      .pipe(map(responseData => {
        return responseData;
      }));
  }

  fetchAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(environment.apiURL + '/api/authors')
      .pipe(map(responseData => {
        let authors: Author[] = [];
        for (const key in responseData) {
          if (responseData.hasOwnProperty(key)) {
            let data = {...responseData[key]};
            for (const key2 in data) {
              authors.push({...data[key2]});
            }
          }
        }
        return authors;
      }));
  }

  updateAuthor(uuid: string, name: string): Observable<any> {
    const authorData = {name: name};
    return this.http.put(environment.apiURL + '/api/authors/' + uuid, authorData, {observe: 'response'});
  }

  deleteAuthor(uuid: string): Observable<any> {
    return this.http.delete(environment.apiURL + '/api/authors/' + uuid, {observe: 'response'});
  }

}
