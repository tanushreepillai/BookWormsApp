import axios from "axios";

export default {
    // Search Google for books
    searchBooks: function (query) {
        return axios.get("https://www.googleapis.com/books/v1/volumes?q=" + query);
    }
};