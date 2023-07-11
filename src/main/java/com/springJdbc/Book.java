package com.springJdbc;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    int bookId;

    String title;

    String author;

    int pages;

/*
    Adventures of Tom Sawyer: Mark Twain	Alice in Wonderland: Lewis Carrol
    Agni Veena: Kazi Nasrul Islam	Ancient Mariner: Coleridge
    Animal Farm: George Orwell	Arms and the Man: G.B.Shaw
    Ben Hur: Lewis Wallace
    Baburnama: Babur
 */


}
