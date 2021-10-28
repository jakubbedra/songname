import {getBackendUrl} from "../js/config.js";

export class AudioPlayer {

    uuid;
    audio;
    paused;
    timeLimit;
    points;

    constructor() {
        this.audio = new Audio();
        this.paused = false;
        this.timeLimit = 1;
        this.points = 10;
    }

    fetchSong(uuid) {
        if (this.uuid == null || this.uuid !== uuid) {
            this.uuid = uuid;
            if (!this.audio.paused) {
                this.audio.pause();
            }
            this.audio = new Audio(getBackendUrl() + '/api/songs/' + this.uuid + '/file');
            this.audio.volume = 0.3;
            this.audio.load();
        }
    }

    incrementTime(seconds) {
        this.timeLimit += seconds;
        if (this.points >= 0) {
            this.points--;
        }
    }

    async playWithTimeLimit() {
        this.audio.play();
        await this.delay(this.timeLimit);
        this.audio.pause();
        this.audio.currentTime = 0;
    }

    delay(seconds) {
        return new Promise(resolve => setTimeout(resolve, seconds * 1000));
    }

    playPause() {
        if (this.audio.paused) {
            this.audio.play();
        } else {
            this.audio.pause();
        }
    }

}
