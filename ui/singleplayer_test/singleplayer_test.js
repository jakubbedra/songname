import {
    clearElementChildren,
    createButtonCell,
} from '../js/dom_utils.js';
import {getBackendUrl} from "../js/config.js";
import {AudioPlayer} from "../audio_player/audio_player.js";

let songJson;

window.addEventListener('load', () => {
    fetchRandomSong();
    const guessForm = document.getElementById('guessForm');
    guessForm.addEventListener('submit', event => takeGuessAction(event));
});

function takeGuessAction(event) {
    event.preventDefault();

    if (songJson.title === document.getElementById('title').value
        && songJson.author === document.getElementById('author').value) {
        alert('you win!');
    }

    //todo: add actual game logic XDDDD
    //and aswell make a regex instead of actuall string, because it will not be fun :(
    /*
        const xhttp = new XMLHttpRequest();
        xhttp.open("POST", getBackendUrl() + '/api/songs', true);
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 201) {
                fetchAndDisplaySongs();
            }
        }
        let request = new FormData();
        request.append("file", document.getElementById('file').files[0]);
        request.append("info", new Blob([JSON.stringify({
            'title': document.getElementById('title').value,
            'authorUuid': getParameterByName('author')
        })], {type: "application/json"}));
        console.log('dupa\n' + document.getElementById('file').files[0].name);
        xhttp.send(request);
        event.target.reset();*/
}

function fetchRandomSong() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            updateJson(JSON.parse(this.responseText));
            displayAudio(songJson);//todo: fix it, make it cleaner, I found out what was wrong: I just did not append the row to table xd
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/songs/random', true);
    xhttp.send();
}

function updateJson(song) {
    songJson = song;
    console.log(songJson);
}

function displayAudio(song) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    tableBody.appendChild(createTableRow(song));// <------------------- this little shit here
}

function createTableRow(song) {
    let tr = document.createElement('tr');
    let audioPlayer = new AudioPlayer();
    tr.appendChild(createButtonCell('play', () => {
        //console.log(song.uuid);
        audioPlayer.fetchSong(song.uuid);
        audioPlayer.playWithTimeLimit();
    }));
    tr.appendChild(createButtonCell('more seconds', () => audioPlayer.incrementTime(1)));
    return tr;
}
