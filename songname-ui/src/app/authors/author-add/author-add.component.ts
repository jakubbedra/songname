import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AuthorsService} from "../authors.service";

@Component({
  selector: 'app-author-add',
  templateUrl: './author-add.component.html',
  styleUrls: ['./author-add.component.css']
})
export class AuthorAddComponent implements OnInit {

  @Output() updateListEvent = new EventEmitter<void>();

  constructor(private authorsService: AuthorsService) {
  }

  ngOnInit(): void {
  }

  onCreateAuthor(name: string) {
    this.authorsService.addAuthor(name).subscribe(response => {
      if (response.status === 201) {
        this.updateListEvent.emit();
      }
    });
  }

}
