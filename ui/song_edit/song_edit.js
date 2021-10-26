import {getParameterByName} from "../js/dom_utils.js";
import {getBackendUrl} from "../js/config.js";

window.addEventListener('load', () => {
    const songForm = document.getElementById('songForm');
    songForm.addEventListener('submit', event => updateSongAction(event));
    const fileForm = document.getElementById('fileForm');
    fileForm.addEventListener('submit', event => updateFileAction(event));

    fetchAndDisplaySong();
});

function fetchAndDisplaySong() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    }
    xhttp.open("GET", getBackendUrl() + '/api/songs/' + getParameterByName('song'));
    xhttp.send();
}

function updateSongAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + '/api/songs/' + getParameterByName('song'), true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySong();
        }
    }
    const request = {
        'title': document.getElementById('title').value,
        'authorUuid': getParameterByName('author')
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function updateFileAction(event) {
    event.preventDefault();
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + '/api/songs/' + getParameterByName('song') + '/file', true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySong();
        }
    }
    let request = new FormData();
    request.append("songFile", document.getElementById('file').files[0]);
    xhttp.send(request);
}