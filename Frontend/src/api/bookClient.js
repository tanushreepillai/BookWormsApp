import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
 */
        export default class BookClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getBook', 'getAllBooks', 'saveBook', 'updateBook', 'deleteBook'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    /**
     * Gets the concert for the given ID.
     * @param key Unique identifier for a book
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The concert
     */
    async getBook(key, errorCallback) {
        try {
            const response = await this.client.get(`/books/${key}`);
            return response.data;
        } catch (error) {
            this.handleError("getBook", error, errorCallback)
        }
    }

    async getAllBooks(id, errorCallback) {
        console.log("inside getAllBooks in client")
        try {
            const response = await this.client.get(`/books/all`);
            return response.data;
        } catch (error) {
            this.handleError("getAllBooks", error, errorCallback)
        }
    }

    async searchBooks(url, errorCallback) {
        console.log("inside client: ");
        try {
            const response = await this.client.get(`/books/search`, {
                url: url
            });
            return response.data;
        } catch (error) {
            this.handleError("searchBooks", error, errorCallback);
        }
    }

    async saveBook(book, errorCallback) {
        try {
            const response = await this.client.post(`books`, {
                title: book.title,
                author: book.author,
                description: book.description,
                imageLink: book.imageLink,
                infoLink: book.infoLink,
                finishedReading: true
            });
            return response.data;
        } catch (error) {
            this.handleError("saveBook", error, errorCallback);
        }
    }

    async updateBook(book, errorCallback) {
        try {
            const response = await this.client.post(`books`, {
                title: book.title,
                author: book.author,
                description: book.description,
                imageLink: book.imageLink,
                infoLink: book.infoLink,
                finishedReading: true
            });
            return response.data;
        } catch (error) {
            this.handleError("updateBook", error, errorCallback);
        }
    }

    async deleteBook(bookId, errorCallback) {
        try {
            const response = await this.client.delete(`/books/${bookId}`, {
                name: name
            });
            return response.data;
        } catch (error) {
            this.handleError("deleteBook", error, errorCallback);
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param method
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
}
