import {clearElementChildren, getParameterByName, createButtonCell, createTextCell} from "../js/dom_utils.js";
import {getBackendUrl} from "../js/config.js";
import {AudioPlayer} from "../audio_player/audio_player.js";

var turn;
var audioPlayer = new AudioPlayer();

window.addEventListener('load', () => {
    fetchTurnInfo().then(i => {
        turn = i;
        fetchAndDisplayPlayers();
        displaySong();
    });
    const guessForm = document.getElementById('guessForm');
    guessForm.addEventListener('submit', event => takeGuessAction(event));
});

function finishTurn() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/game/'
        + getParameterByName('gameUuid') + '/turn/next', true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let goNext = JSON.parse(this.responseText);
            if (goNext === true) {
                window.location.href = '../local_game/local_game.html?gameUuid=' + getParameterByName('gameUuid');
            } else {
                window.location.href = '../game_over/game_over.html?gameUuid=' + getParameterByName('gameUuid');
            }
        }
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send();
}

function takeGuessAction(event) {
    let button = document.getElementById('guessButton');
    button.value = 'NEXT TURN';
    button.onclick = finishTurn;
    button.disabled = true;

    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/game/check', true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let guessed = JSON.parse(this.responseText);
            let infoHeader = document.getElementById('infoHeader');
            if (guessed === true) {
                infoHeader.textContent = "Correct!";
                updatePlayerPoints();
                console.log('You win!!'); //implement actual logic later on
            } else {
                infoHeader.textContent = "You lose!";
                console.log('you lose!');
            }
            audioPlayer.points = 0;
        }
    };
    const request = {
        "authorName": document.getElementById('author').value,
        "title": document.getElementById('title').value,
        "uuid": turn.songUuid
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
    button.disabled = false;
}

function updatePlayerPoints() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + '/api/players/' + turn.player.uuid, true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPlayers();
        }
    };
    const request = {
        "points": audioPlayer.points
    };
    console.log('player points: ' + audioPlayer.points);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

async function fetchTurnInfo() {
    const response = await fetch(getBackendUrl() + '/api/game/'
        + getParameterByName('gameUuid') + '/turn', {});
    return response.json();
}

function fetchAndDisplayPlayers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPlayers(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/game/' + getParameterByName('gameUuid') + '/players', true);
    xhttp.send();
}

function displayPlayers(players) {
    let tableBody = document.getElementById('playersTableBody');
    clearElementChildren(tableBody);
    players.players.forEach(player => {
        tableBody.appendChild(createTableRow(player));
    });
}

function displaySong() {
    let tableBody = document.getElementById('songTableBody');
    clearElementChildren(tableBody);
    tableBody.appendChild(createSongTableRow(turn.songUuid));
}

function createTableRow(player) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(player.name));
    tr.appendChild(createTextCell(player.score));
    if (player.uuid === turn.player.uuid) {
        tr.classList.add('current-player');
    }
    return tr;
}

function createSongTableRow(song) {
    let tr = document.createElement('tr');
    tr.appendChild(createButtonCell('play', () => {
        audioPlayer.fetchSong(song);
        audioPlayer.playWithTimeLimit();
    }));
    tr.appendChild(createButtonCell('more seconds', () => audioPlayer.incrementTime(1)));
    return tr;
}
