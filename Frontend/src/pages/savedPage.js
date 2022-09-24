import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import BookClient from "../api/bookClient";
import API from "../util/API";

/**
 * Logic needed for the view playlist page of the website.
 */
class SavedPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['getBook', 'getAllBooks', 'updateBook', 'deleteBook'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        // document.getElementById('find').addEventListener('click', this.handleBookSearch);
        // document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.client = new BookClient();
        // await this.handleBookSearch();

        // this.dataStore.addChangeListener(this.renderExample)
    }

    // Render Methods --------------------------------------------------------------------------------------------------



    async renderSavedBooks(title) {
        const booksArray = this.client.getAllBooks();
        let savedResults = document.getElementById("savedResults");

        if (booksArray) {
            let myHtml="";
            for(let i in booksArray) {
                const book = booksArray[i];
                let imageLink = null
                try {
                    imageLink = book.imageLinks.smallThumbnail
                } catch (err) {
                    continue
                }

                myHtml += `
                <div></div>
                <div id="title">Title: ${book.title}</div>
                <div>Author: ${book.authors[0]}</div>
                <div><img src = ${imageLink}></div>
                <div></div>
                <div>Description: ${book.description}</div>
                <button id="delete" data-index="${i}">Delete book</button>
                `
            }

            savedResults.innerHTML = "";
            savedResults.innerHTML = myHtml;

        }
        else {
            resultTable.innerHTML = "<tr><td>No books found</td></tr>"
        }

        document.getElementById('delete').addEventListener(
            'click', event => this.deleteBook(event));

    }

    async getBook(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("savedResults").value;

        let result = await this.client.getBook(key, this.errorHandler);
        this.dataStore.set("example", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async getAllBooks(event) {

    }

    async updateBook(event) {

    }

    async deleteBook(event) {

    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const examplePage = new ExamplePage();
    await examplePage.mount();
};

window.addEventListener('DOMContentLoaded', main);

