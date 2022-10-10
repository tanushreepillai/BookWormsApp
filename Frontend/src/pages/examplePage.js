import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import BookClient from "../api/bookClient";
import API from "../util/API";

/**
 * Logic needed for the view playlist page of the website.
 */
class ExamplePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['saveBook', 'renderSearchResults', 'handleBookSearch'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('search').addEventListener('click', this.handleBookSearch);
        // document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.client = new BookClient();
        // await this.handleBookSearch();

        // this.dataStore.addChangeListener(this.renderExample)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async handleBookSearch(event) {  // todo return top 10 results
        event.preventDefault()
        let title = document.getElementById('search-name-field').value;
        let author = document.getElementById('search-author-field').value

        if (author !== "")  {
            if (title === "") {
                author = "inauthor:" + author;
            } else {
                author = "+inauthor:" + author;
            }
        }

        let searchRequest = title + author;

        searchRequest.replaceAll(" ", "%20");

        let data = await this.client.searchBooks(searchRequest);
        console.log({data})

        let bookArray = [];

        for (let i in data.items) {
            bookArray.push(data.items[i].volumeInfo);
        }

        this.dataStore.set("books", bookArray);

        await this.renderSearchResults(title);
    }

    async renderSearchResults() {
        let resultTable = document.getElementById("search-results");
        let searchedBooks = this.dataStore.get("books");

        if (searchedBooks) {
            let myHtml="";
            for(let i in searchedBooks) {
                const book = searchedBooks[i];
                let imageLink = null
                try {
                    imageLink = book.imageLinks.smallThumbnail
                } catch (err) {
                    continue
                }

                myHtml += `
                <div></div>
                <div>Title: ${book.title}</div>
                <div>Author: ${book.authors[0]}</div>
                <div><img src = ${imageLink} alt="image"></div>
                <div></div>
                <div>Description: ${book.description}</div>
                <button id="save" data-index="${i}">Save book</button>
                `
            }

            resultTable.innerHTML = "";
            resultTable.innerHTML = myHtml;

        }
        else {
            resultTable.innerHTML = "<tr><td>No books found. Please check your spelling.</td></tr>"
        }

        document.getElementById('save').addEventListener(
            'click', event => this.saveBook(event));

    }

    async saveBook(event) {
        event.preventDefault()
        const booksArray = this.dataStore.get("books");
        const book = booksArray[event.target.dataset.index];
        let imageLink = null;

        try {
            imageLink = book.imageLinks.smallThumbnail
        } catch (err) {

        }

        let author = book.authors[0];

        let bookToSave = {
            title: book.title,
            author: author,
            description: book.description,
            imageLink: imageLink,
            // finishedReading: false
        }

        await this.client.saveBook(bookToSave);
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

