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
//        data.put(book.getBookId(),book );
        String query = "INSERT INTO Book (title, author, pages) VALUES ('"+title+"','"+author+"' , "+pages+");";

        int insertRow = jdbcTemplate.update(query);

        return insertRow;
    }

    public Book getById(int id) throws Exception {
        String query = "SELECT * from Book where id ="+id+";";

        Book book = new Book();

        SqlRowSet srs = jdbcTemplate.queryForRowSet(query);
        //SqlRowSet srs = jdbcTemplate.queryForRowSet("select id, name from employee where id > ?", parameters);
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
        String query = "UPDATE Book SET title = '"+title +"',author ='"+author+"',pages ="+ pages + "WHERE id ="+id+ ";";
//        String query = "UPDATE Book SET title = 'Agni Veena',author ='Kazi Nasrul Islam',pages = 540 WHERE id = "+id+ ";";

        int updateRow = jdbcTemplate.update(query);

        return updateRow;

    }

    public int removeById(int id) {
        String query = "DELETE FROM Book WHERE id = "+id+";";
        int deletedRow = jdbcTemplate.update(query);

        return deletedRow;

    }




}
