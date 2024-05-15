package com.EbookApi.apiEBook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long countDownload;
    private String language;
    private String genero;
    private Boolean copyright;
    @ManyToOne
    private Author author;

    public Book() {
    }

    public Book(Author author, Boolean copyright, String genero, String language, Long countDownload, String title) {
        this.author = author;
        this.copyright = copyright;
        this.genero = genero;
        this.language = language;
        this.countDownload = countDownload;
        this.title = title;

    }
}
