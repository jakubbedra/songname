import {
    getParameterByName,
    clearElementChildren,
    createTextCell,
    createButtonCell,
    createLinkCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from "../js/config.js";

window.addEventListener('load', () => {
    console.log('dupa');
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
        + getParameterByName('author') + '/songs', true);
    xhttp.send();
}

function displaySongs(songs) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    console.log(songs);
    (songs.songs).forEach(song => {
        tableBody.appendChild(createTableRow(song));
    });
}

function createTableRow(song) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(song.uuid));
    tr.appendChild(createTextCell(song.title));
    tr.appendChild(createButtonCell('play', () => fetchAndPlaySong(song.uuid)));
    tr.appendChild(createLinkCell('edit', '../song_edit'));
    tr.appendChild(createButtonCell('delete', () => deleteSong(song.uuid)));
    return tr;
}

function deleteSong(song) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySongs();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/songs/' + song, true);
    xhttp.send();
}

function fetchAndDisplayAuthor() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAuthor(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + getParameterByName('author'), true);
    xhttp.send();
}

function displayAuthor(author) {
    setTextNode('author-name', author.name);
    setTextNode('uuid', author.uuid);
    setTextNode('name', author.name);
}

function fetchAndPlaySong(uuid) {
    playSong(uuid);
}

async function playSong(uuid) {
    let audio = new Audio(getBackendUrl() + '/api/songs/' + uuid + '/file');
    audio.load();
    audio.volume = 0.3;
    audio.play();
    await delay(15000);
    audio.pause();
}

function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
