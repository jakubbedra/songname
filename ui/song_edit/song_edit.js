import {getParameterByName} from "../js/dom_utils.js";
import {getBackendUrl} from "../js/config.js";

window.addEventListener('load', () => {
    const songForm = document.getElementById('songForm');
    songForm.addEventListener('submit', event => updateSongAction(event));

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
    xhttp.open("GET",getBackendUrl() + '/api/songs/' + getParameterByName('song'));
    console.log(getParameterByName('song'));
    xhttp.send();
}