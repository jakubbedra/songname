import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AuthorsService} from "../authors.service";
import {Author} from "../author.model";

@Component({
  selector: 'app-author-view',
  templateUrl: './author-view.component.html',
  styleUrls: ['./author-view.component.css']
})
export class AuthorViewComponent implements OnInit {

  authorUuid: string;
  author: Author;

  constructor(
    private authorsService: AuthorsService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.authorUuid = this.route.snapshot.params['uuid'];
    this.fetchAuthor();
  }

  fetchAuthor() {
    this.authorsService.fetchAuthor(this.authorUuid).subscribe(response => {
      console.log(response);
      this.author = response;
    });
  }

}
