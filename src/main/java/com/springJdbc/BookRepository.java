package com.springJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addBook(String author, String title,  int pages) {
//        String query = "INSERT INTO book (title, author, pages) VALUES ('"+title+"','"+author+"' , "+pages+");";
//        int insertRow = jdbcTemplate.update(query);

        String query = "INSERT INTO book (title, author, pages) VALUES (?,?,?);";
        int insertRow = jdbcTemplate.update(query,new Object[]{title,author,pages});

        return insertRow;
    }

    public Book getById(int id) throws Exception {
//        String query = "SELECT * from book where id ="+id+";";
        String query = "SELECT * from book where id = ? ;";

        Book book = new Book();

        SqlRowSet srs = jdbcTemplate.queryForRowSet(query,new Object[]{id});
//        SqlRowSet srs = jdbcTemplate.queryForRowSet(query);

        int rowCount = 0;
        while (srs.next()) {
            book.setBookId(srs.getInt("id"));
            book.setAuthor(srs.getString("author"));
            book.setTitle(srs.getString("title"));
            book.setPages(srs.getInt("pages"));

            rowCount++;
        }

        System.out.println("The number of rowSet : "+rowCount);

        return book;
    }

    public int updateBook(int id, String title, String author, Integer pages){

//        String query = "UPDATE book SET title = '" +title + "', author ='" + author + "',pages = "+ pages + " WHERE id = "+id+ ";";
//        int updateRow = jdbcTemplate.update(query);

        String query = "UPDATE book SET title = ?, author = ?,pages = ? WHERE id = ? ;";
        int updateRow = jdbcTemplate.update(query,new Object[]{title,author,pages,id});
        return updateRow;
    }

    public int removeById(int id) {
//        String query = "DELETE FROM Book WHERE id = "+id+";";
//        int deletedRow = jdbcTemplate.update(query);

        String query = "DELETE FROM Book WHERE id = ? ;";
        int deletedRow = jdbcTemplate.update(query,new Object[]{id});


        return deletedRow;
    }




}
