package com.EbookApi.apiEBook.service;

import com.EbookApi.apiEBook.dto.BookDto;
import com.EbookApi.apiEBook.repository.AuthorRepository;



public class AuthorService {

    AuthorRepository authorRepository;
    TransformData transformData=new TransformData();
    final String urlBase="https://gutendex.com/books?";

    public BookDto findByTitle(String title){


        return null;

    }

}
