import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ExampleClient from "../api/exampleClient";
import API from "../util/API";

/**
 * Logic needed for the view playlist page of the website.
 */
class ExamplePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['getBook', 'getAllBooks', 'saveBook', 'updateBook', 'deleteBook'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('find').addEventListener('click', this.handleBookSearch);
        // document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.client = new ExampleClient();
        // await this.handleBookSearch();

        // this.dataStore.addChangeListener(this.renderExample)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async handleBookSearch(event) {  // todo return top 10 results
        let title = document.getElementById('search-name-field').value
        let author = document.getElementById('search-author-field').value
        console.log(title + " " + author)
        let query = "forward%20the%20foundation+inauthor:asimov&key=AIzaSyAmwU-FhO1HLhFjungcYPqfxr7jAbk5faE";
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

        await this.renderSearchResults()
    }

    async renderSearchResults() {
        const booksArray = this.dataStore.get("books")
        let resultTable = document.getElementById("all-posts-result");

        if (booksArray) {
            let myHtml="";
            for(let book of booksArray) {
                let imageLink = null
                try {
                    imageLink = book.imageLinks.smallThumbnail
                } catch (err) {
                    continue
                }

                if (book.title.includes("Foundation")) {
                    myHtml += `
                    <div></div>
                    <div>Title: ${book.title}</div>
                    <div>Author: ${book.authors[0]}</div>
                    <div><img src = ${imageLink}></div>
                    <div></div>
                    <div>Description: ${book.description}</div>
                    `
                }

            }

            resultTable.innerHTML = myHtml;

        }
        else {
            resultTable.innerHTML = "<tr><td> no one attending.. </td></tr>"
        }

    }

    async getBook(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("example", null);

        let result = await this.client.getExample(id, this.errorHandler);
        this.dataStore.set("example", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async getAllBooks(event) {

    }

    async saveBook(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("example", null);

        let name = document.getElementById("create-name-field").value;

        const createdExample = await this.client.createExample(name, this.errorHandler);
        this.dataStore.set("example", createdExample);

        if (createdExample) {
            this.showMessage(`Created ${createdExample.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
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

