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
        this.bindClassMethods(['renderSavedBooks', 'getBook', 'updateBook', 'deleteBook'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        // document.getElementById('find').addEventListener('click', this.handleBookSearch);
        // document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.client = new BookClient();
        await this.renderSavedBooks();

        // this.dataStore.addChangeListener(this.renderExample)
    }

    // Render Methods --------------------------------------------------------------------------------------------------


    async renderSavedBooks() {
        let booksArray = await this.client.getAllBooks();
        let savedResults = document.getElementById("savedResults");
        console.log("insider render saved books");

        if (booksArray) {
            console.log(booksArray);
            let myHtml="";
            for(let i in booksArray) {
                const book = booksArray[i];
                let imageLink = null
                let bookId = `${book.title}${book.authors.slice(0,1)}`
                try {
                    imageLink = book.imageLinks;
                } catch (err) {
                    continue
                }

                myHtml += `
                <div></div>
                <div id="title">Title: ${book.title}</div>
                <div>Author: ${book.authors}</div>
                <div><img src = ${imageLink}></div>
                <div></div>
                <div>Description: ${book.description}</div>
                <button id="delete" name="${bookId}" data-index="${i}">Delete book</button>
                `
            }

            savedResults.innerHTML = "";
            savedResults.innerHTML = myHtml;

        }
        else {
            savedResults.innerHTML = "<tr><td>No books found</td></tr>"
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

    async updateBook(event) {

    }

    async deleteBook(event) {
        // current book ID is the string of the title plus the first char of the author's name
        event.preventDefault()

        let bookId = event.target.name;
        console.log("bookId: " + bookId);
        await this.client.deleteBook(bookId);
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const savedPage = new SavedPage();
    await savedPage.mount();
};

window.addEventListener('DOMContentLoaded', main);

