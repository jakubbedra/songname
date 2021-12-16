import {clearElementChildren, createButtonCell, createTextCell} from "../js/dom_utils.js";
import {getBackendUrl} from "../js/config.js";

var gameUuid;

window.addEventListener('load', () => {
    requestGameUuid().then(r => {
        gameUuid = r.replaceAll("\"", "");
        fetchAndDisplayPlayers();
        const playerForm = document.getElementById('playerForm');
        playerForm.addEventListener('submit', event => createPlayerAction(event));
        const startButton = document.getElementById('start');
        startButton.addEventListener('click', startGame);
    });
});

async function requestGameUuid() {
    const response = await fetch(getBackendUrl() + "/api/game/uuid", {});
    return response.text();
}

function fetchAndDisplayPlayers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPlayers(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/game/' + gameUuid + '/players', true);
    xhttp.send();
}

function startGame() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            window.location.href = '../local_game/local_game.html?gameUuid=' + gameUuid;
        }
    };
    let turns = document.getElementById('turns').value;
    xhttp.open("POST", getBackendUrl() + '/api/game/' + gameUuid + '/create?turns=' + turns, true);
    xhttp.send();
}

function displayPlayers(players) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    players.players.forEach(player => {
        tableBody.appendChild(createTableRow(player));
    });
}

function createPlayerAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/players', true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            fetchAndDisplayPlayers();
        }
    };

    const request = {
        'gameUuid': gameUuid,
        'name': document.getElementById('name').value
    };

//    console.log(request);

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
    event.target.reset();
}

function createTableRow(player) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(player.name));
    tr.appendChild(createButtonCell('delete', () => deletePlayer(player.uuid)));
    return tr;
}

function deletePlayer(player) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPlayers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/players/' + player, true);
    xhttp.send();
}
