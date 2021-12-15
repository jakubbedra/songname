import {Component, OnInit} from '@angular/core';
import {Author} from "../author.model";
import {AuthorsService} from "../authors.service";

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements OnInit {

  authors: Author[];

  constructor(private authorsService: AuthorsService) {
  }

  ngOnInit(): void {
    this.fetchAuthors();
  }

  fetchAuthors() {
    this.authorsService.fetchAuthors().subscribe(authors => {
      this.authors = authors;
    });
  }

  onDeleteAuthor(uuid: string) {
    this.authorsService.deleteAuthor(uuid).subscribe(authors => {
      this.fetchAuthors();
    });
  }

}
