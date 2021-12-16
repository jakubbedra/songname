import {Author} from "../authors/author.model";

export class Song {
  public uuid: string;
  public title: string;
  //public authors: Author[];

  constructor(uuid: string, title: string, /*authors: Author[]*/) {
    this.uuid = uuid;
    this.title = title;
    //this.authors = authors;
  }
}
