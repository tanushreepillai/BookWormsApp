package com.kenzie.appserver.service.model;

import java.util.List;

public class Books {

        private final String imageLink;

        private final String description;

        private final String author;

        private final String title;

        private boolean finishedReading;

        private final String bookId;

        public Books(String imageLink, String description, String author, String title, boolean finishedReading) {
            String bookId = title + author;

            this.imageLink = imageLink;
            this.description = description;
            this.author = author;
            this.title = title;
            this.finishedReading = finishedReading;
            this.bookId = bookId;
        }

        public String getImageLinks() {
            return imageLink;
        }

        public String getDescription() {
            return description;
        }

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public boolean isCompleted() {
            return finishedReading;
        }

        public void setCompleted(boolean completed) {
            finishedReading = completed;
        }

        public String getBookId() {
            return bookId;
        }

    }

