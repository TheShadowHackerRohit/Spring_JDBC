package com.springJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository ;

    public int addBook(String author, String title,  int pages) {

        return bookRepository.addBook(author,title, pages);
    }

    public Book getBook(int id) throws Exception {

        return bookRepository.getById(id);
    }

    public int  updateBook(int id, String title, String author, Integer pages) {
        int updatedRow = bookRepository.updateBook(id,title,author,pages);
        return updatedRow;
    }

    public boolean deleteBook(int id) throws BookNotFoundException {
        bookRepository.removeById(id);
        return true;
    }
}
