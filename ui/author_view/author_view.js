import {
    getParameterByName,
    clearElementChildren,
    createTextCell,
    createButtonCell,
    createLinkCell,
    setTextNode
} from '../js/dom_utils';
import {getBackendUrl} from "../js/config";

window.addEventListener('load', () => {
    fetchAndDisplayAuthor();
    fetchAndDisplaySongs();
});

function fetchAndDisplaySongs() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySongs(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/'
        + getParameterByName('uuid') + '/songs', true);
    xhttp.send();
}

function displaySongs(songs) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    songs.songs.forEach(song => {
        tableBody.appendChild(createTableRow(song));
    });
}

function createTableRow(song) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(song.uuid));
    tr.appendChild(createTextCell());
    tr.appendChild(createLinkCell('edit', '../song_edit'));
}

/*
    todo
 */

