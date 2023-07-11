package com.springJdbc;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RequestMapping("/jdbc")
@RestController
public class BookController {

    BookService bookService = new BookService();

    @PostMapping("/add-book")
    public ResponseEntity addBook(@RequestParam String author, @RequestParam String title, @RequestParam int pages){
        try {
            int updatedRow = bookService.addBook(author,title, pages);
            return new ResponseEntity("Row inserted successfully "+updatedRow, HttpStatus.CREATED);
        } catch (BookAlreadyExistsException exp){
            return new ResponseEntity("unable to add book as it already exists",HttpStatus.valueOf(400)) ;
        }catch (Exception exp){
            return  new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
        }
    }

    @GetMapping("/find-book")// ? id = 1
    public ResponseEntity findBook(@RequestParam int id){
        try {
            Book book = bookService.getBook(id);
            return new ResponseEntity(book,HttpStatus.OK);
        } catch (BookNotFoundException exp){
            return  new ResponseEntity("book does not found",HttpStatus.valueOf(500));
        }catch (Exception exp){
            return  new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
        }

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
    public String updateBook(@RequestParam int id, @RequestParam String title, @RequestParam String author, @RequestParam int pages){
        try {
            int  updateBook = bookService.updateBook(id,title,author,pages);
            return "book updated successfully";
        }catch (Exception exp){
            return "Exception occurred";
        }

    }

    @DeleteMapping("/remove-book/{id}")
    public ResponseEntity deleteBook(@PathVariable int id){
        try{
            boolean isDeleted = bookService.deleteBook(id);
            return new ResponseEntity(" book is removed successfully",HttpStatus.OK);

        }catch (BookNotFoundException exp){
            return new ResponseEntity(exp.getMessage(),HttpStatus.NOT_FOUND);
        }


    }
}

