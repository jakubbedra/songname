import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from "../js/dom_utils.js";
import {getBackendUrl} from "../js/config.js";

window.addEventListener('load', () => {
    fetchAndDisplayAuthors();
});

function fetchAndDisplayAuthors() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAuthors(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors', true);
    console.log(getBackendUrl() + '/api/authors');
    xhttp.send();
}

function displayAuthors(authors) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    authors.authors.forEach(author => {
        tableBody.appendChild(createTableRow(author));
    });
}

function createTableRow(author) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(author.name));
    tr.appendChild(createLinkCell('view', '../author_view/author_view.html?author=' + author.uuid));
    //console.log(author.uuid);
    tr.appendChild(createButtonCell('delete', () => deleteAuthor(author.uuid)));
    return tr;
}

function deleteAuthor(author) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayAuthors();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/authors/' + author, true);
    xhttp.send();
}
