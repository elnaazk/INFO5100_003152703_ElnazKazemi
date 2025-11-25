package edu.elnaazk.exercise8;

import java.util.ArrayList;
import java.util.List;

public class BookShelf {
    private List<Book> books = new ArrayList<>();

    public BookShelf() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}