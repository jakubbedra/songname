import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthorsService} from "../authors.service";

@Component({
  selector: 'app-author-edit',
  templateUrl: './author-edit.component.html',
  styleUrls: ['./author-edit.component.css']
})
export class AuthorEditComponent implements OnInit {

  authorUuid: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authorsService: AuthorsService
  ) {
  }

  ngOnInit(): void {
    this.authorUuid = this.route.snapshot.params['uuid'];
  }

  onSaveChanges(name: string) {
    this.authorsService.updateAuthor(this.authorUuid, name).subscribe(response => {
    });
  }

}
