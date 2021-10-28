import {clearElementChildren, createButtonCell, createTextCell, getParameterByName} from "../js/dom_utils.js";
import {getBackendUrl} from "../js/config.js";

window.addEventListener('load', () => {
    fetchAndDisplayResults();
    const startButton = document.getElementById('exit');
    startButton.addEventListener('click', exitGame);
});

function fetchAndDisplayResults() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPlayers(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/game/' + getParameterByName('gameUuid') + '/results', true);
    xhttp.send();
}

function exitGame() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            window.location.href = '../players_list/players_list.html';
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/game/' + getParameterByName('gameUuid') + '/finish', true);
    xhttp.send();
}

function displayPlayers(players) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    var place = 1;
    players.players.forEach(player => {
        tableBody.appendChild(createTableRow(player, place));
        place++;
    });
}

function createTableRow(player, place) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(place));
    tr.appendChild(createTextCell(player.name));
    tr.appendChild(createTextCell(player.score));
    return tr;
}
