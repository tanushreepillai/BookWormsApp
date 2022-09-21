import axios from "axios";

export default {
    // Search Google for books
    searchBooks: function (query) {
        return axios.get("https://www.googleapis.com/books/v1/volumes?q=foundation+inauthor:asimov&key=AIzaSyAmwU-FhO1HLhFjungcYPqfxr7jAbk5faE");
    },
    // Gets all books from db
    getBooks: function() {
        return axios.get("/api/books");
    },
    // Gets the book with the given id
    getBook: function(id) {
        return axios.get("/api/books/" + id);
    },
    // Deletes the book with the given id
    deleteBook: function(id) {
        return axios.delete("/api/books/" + id);
    },
    // Saves a book to the database
    saveBook: function(bookData) {
        return axios.post("/api/books", bookData);
    }
};