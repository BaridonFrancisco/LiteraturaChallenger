package com.EbookApi.apiEBook.service;

import com.EbookApi.apiEBook.dto.BookDto;
import com.EbookApi.apiEBook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    TransformData transformData=new TransformData();
    final String urlBase="https://gutendex.com/books?";

    public BookDto findByTitle(String title){
        var book=authorRepository.findByTitle(title);
        if(book.isEmpty()){
            // que busque en la api lo agrege en la base , lo retorne
            // sino lo encuentra null
        }

        return null;

    }

}
