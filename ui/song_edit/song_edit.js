import {getParameterByName} from "../js/dom_utils.js";
import {getBackendUrl} from "../js/config.js";

window.addEventListener('load', () => {
    const songForm = document.getElementById('songForm');
    songForm.addEventListener('submit', event => updateSongAction(event));

    fetchAndDisplaySong();
});

