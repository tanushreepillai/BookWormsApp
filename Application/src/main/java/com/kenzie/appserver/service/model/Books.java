package com.kenzie.appserver.service.model;

import java.util.List;

public class Books {

        private String imageLinks;

        private String description;

        private List<String> authors;

        private String title;

        private String infoLink;

        private boolean isCompleted;

        private String bookId;

        public Books(String imageLinks, String description, List<String> authors, String title, String infoLink, boolean isCompleted, String bookId) {
            this.imageLinks = imageLinks;
            this.description = description;
            this.authors = authors;
            this.title = title;
            this.infoLink = infoLink;
            this.isCompleted = isCompleted;
            this.bookId = bookId;
        }

        public String getImageLinks() {
            return imageLinks;
        }

        public String getDescription() {
            return description;
        }

        public List<String> getAuthors() {
            return authors;
        }

        public String getTitle() {
            return title;
        }

        public String getInfoLink() {
            return infoLink;
        }

        public boolean isCompleted() {
            return isCompleted;
        }

        public void setCompleted(boolean completed) {
            isCompleted = completed;
        }

        public String getBookId() {
            return bookId;
        }

    }

