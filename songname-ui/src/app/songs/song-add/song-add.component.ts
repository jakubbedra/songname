import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {SongsService} from "../songs.service";

@Component({
  selector: 'app-song-add',
  templateUrl: './song-add.component.html',
  styleUrls: ['./song-add.component.css']
})
export class SongAddComponent implements OnInit {

  @Output() updateSongsListEvent = new EventEmitter<void>();

  authorUuid: string;
  songFile: File;

  constructor(
    private route: ActivatedRoute,
    private songsService: SongsService
  ) {
  }

  ngOnInit(): void {
    this.authorUuid = this.route.snapshot.params['uuid'];
  }

  onSelectFile(event) {
    this.songFile = event.target.files[0]; //MOZE SUBSCRIBE NIE DAJE??
  }

  onCreateSong(title: string) {
    this.songsService.addSong(title, this.authorUuid, this.songFile).subscribe(response => {
      if (response.status === 201) {
        this.updateSongsListEvent.emit();
      }
    })
  }

}
