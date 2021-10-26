import {getParameterByName} from "../js/dom_utils.js";
import {getBackendUrl} from "../js/config.js";

window.addEventListener('load', () => {
    const authorForm = document.getElementById('authorForm');
    authorForm.addEventListener('submit', event => updateAuthorAction(event));

    fetchAndDisplayAuthor();
});

function fetchAndDisplayAuthor() {
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
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + getParameterByName('author'));
    xhttp.send();
}

function updateAuthorAction(event) {
    event.preventDefault();
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + '/api/authors/' + getParameterByName('author'), true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayAuthor();
        }
    }
    const request = {
        'name': document.getElementById('name').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}
