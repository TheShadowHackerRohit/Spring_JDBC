package com.springJdbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RequestMapping("/jdbc")
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add-book")
    public ResponseEntity addBook(@RequestParam String author, @RequestParam String title, @RequestParam int pages) throws Exception{
            int updatedRow = bookService.addBook(author,title, pages);
            return new ResponseEntity("Row inserted successfully "+updatedRow, HttpStatus.CREATED);
    }

    @GetMapping("/find-book")// ? id = 1
    public ResponseEntity findBook(@RequestParam int id) throws Exception{

        Book book = bookService.getBook(id);
        return new ResponseEntity(book,HttpStatus.OK);

    }


//    @GetMapping("/all-books")
//    public List<Book> getAllBooks(){
//        return data.values().stream().toList();//data.values() gives the collections
//    }

    //    @GetMapping("/find-another-book/{id}") // /find-another-book/1
//    public Book findBookByPathVariable(@PathVariable int id){
//        return data.get(id);
//    }
//
    @PutMapping("/update-book")
    public String updateBook(@RequestParam int id, @RequestParam String title, @RequestParam String author, @RequestParam int pages) throws Exception{

        int  updateBook = bookService.updateBook(id,title,author,pages);
        return "book updated successfully";

    }

    @DeleteMapping("/remove-book/{id}")
    public ResponseEntity deleteBook(@PathVariable int id) throws  Exception{

        boolean isDeleted = bookService.deleteBook(id);
        return new ResponseEntity(" book is removed successfully",HttpStatus.OK);

    }
}

