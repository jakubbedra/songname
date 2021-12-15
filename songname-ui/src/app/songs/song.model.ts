import {Author} from "../authors/author.model";

export class Song {
  public uuid: string;
  public name: string;
  public authors: Author[];

  constructor(uuid: string, name: string, authors: Author[]) {
    this.uuid = uuid;
    this.name = name;
    this.authors = authors;
  }
}
