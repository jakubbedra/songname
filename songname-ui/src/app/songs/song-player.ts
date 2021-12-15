import {environment} from "../../environments/environment";

export class SongPlayer {

  getSongFileUrl(uuid: string) {
    return environment.apiURL + '/songs/' + uuid + '/file';
  }

}
