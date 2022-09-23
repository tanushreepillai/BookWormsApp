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
        // document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.client = new ExampleClient();
        await this.handleBookSearch();

        // this.dataStore.addChangeListener(this.renderExample)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async handleBookSearch(event) {  // todo return top 10 results

        let query = "foundation+inauthor:asimov&key=AIzaSyAmwU-FhO1HLhFjungcYPqfxr7jAbk5faE";
        this.dataStore.set("books", null);
        // event.preventDefault();
        await API.searchBooks(query)
            .then(res => {
                if (res.data.status === "error") {
                    throw new Error(res.data.message);
                }

                let booksArray = [];

                for(let i=0; i<res.data.items.length && i<10; i++) {
                    booksArray[i] = res.data.items[i].volumeInfo;
                    // console.log("inside: " + booksArray[i].title);
                }

                this.dataStore.set("books", booksArray)
            })
            .catch(err => console.log(err.message));

        let newArray = this.dataStore.get("books")
        console.log("title: " + newArray[0].title)
    }

    async renderSearchResults(response) {
        // let resultArea = document.getElementById("result-info");
        //
        // const example = this.dataStore.get("example");
        //
        // if (example) {
        //     resultArea.innerHTML = `
        //         <div>ID: ${example.id}</div>
        //         <div>Name: ${example.name}</div>
        //     `
        // } else {
        //     resultArea.innerHTML = "No Item";
        // }
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
    examplePage.mount();
};

window.addEventListener('DOMContentLoaded', main);

