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
        document.getElementById('find').addEventListener('click', this.handleBookSearch);
        // document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.client = new BookClient();
        // await this.handleBookSearch();

        // this.dataStore.addChangeListener(this.renderExample)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async handleBookSearch(event) {  // todo return top 10 results
        let title = null;
        let author = null;
        let query = null;

        try {
            title = document.getElementById('search-name-field').value;
        } catch (err) {
            console.log(err.message);
        }

        try {
            author = document.getElementById('search-author-field').value
        } catch (err) {
            console.log(err.message);
        }

        if (!title && author) {
            query = "inauthor:" + author + "&key=AIzaSyAmwU-FhO1HLhFjungcYPqfxr7jAbk5faE";
        } else if (title && !author) {
            query = title +  "&key=AIzaSyAmwU-FhO1HLhFjungcYPqfxr7jAbk5faE";
        } else if (title && author) {
            query = title + "inauthor:" + author + "&key=AIzaSyAmwU-FhO1HLhFjungcYPqfxr7jAbk5faE";
        } else {
            throw new Error("")
        }


        console.log("query: " + query);
        event.preventDefault()
        await API.searchBooks(query)
            .then(res => {
                if (res.data.status === "error") {
                    throw new Error(res.data.message);
                }

                this.dataStore.set("books", res.data.items.map(item => item.volumeInfo))
            })
            .catch(err => console.log(err.message));
        //
        // const booksArray = this.dataStore.get("books")
        // console.log("size: " + booksArray.length)
        // for (let book of booksArray) {
        //     console.log("title: " + book.title)
        //     console.log("image: " + book.imageLinks.smallThumbnail)
        // }

        await this.renderSearchResults(title)
    }

    async renderSearchResults(title) {
        const booksArray = this.dataStore.get("books")
        let resultTable = document.getElementById("all-posts-result");

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

                if (book.title.toUpperCase().includes(title.toUpperCase())) {
                    myHtml += `
                    <div></div>
                    <div id="title">Title: ${book.title}</div>
                    <div>Author: ${book.authors[0]}</div>
                    <div><img src = ${imageLink}></div>
                    <div></div>
                    <div>Description: ${book.description}</div>
                    <button id="save" data-index="${i}">Save book</button>
                    `
                }
            }

            resultTable.innerHTML = "";
            resultTable.innerHTML = myHtml;

        }
        else {
            resultTable.innerHTML = "<tr><td>No books found</td></tr>"
        }

        document.getElementById('save').addEventListener(
            'click', event => this.saveBook(event));

    }

    async saveBook(event) {
        event.preventDefault()
        const booksArray = this.dataStore.get("books")
        const book = booksArray[event.target.dataset.index]
        let imageLink = null
        try {
            imageLink = book.imageLinks.smallThumbnail
        } catch (err) {

        }
        let bookToSave = {
            title: book.title,
            author: book.authors[0],
            description: book.description,
            imageLink: book.imageLink,
            infoLink: book.infoLink
        }

        await this.client.saveBook(bookToSave)
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

