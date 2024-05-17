package com.EbookApi.apiEBook.service;

import com.EbookApi.apiEBook.dto.AuthorDTO;
import com.EbookApi.apiEBook.dto.BookDto;
import com.EbookApi.apiEBook.model.Author;
import com.EbookApi.apiEBook.model.Book;
import com.EbookApi.apiEBook.model.Gender;
import com.EbookApi.apiEBook.repository.AuthorRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    TransformData transformData = new TransformData();
    final String urlBase = "https://gutendex.com/books?";
    GutendexService gutendexService = new GutendexService();

    public BookDto findByTitle(String title) throws IOException, URISyntaxException {
        var book = authorRepository.findByBook(title);
        if (book.isPresent()) {
            var authorAux = book.get();
            System.out.println(book.get().getListaBook().size());
            var bookAux = book.get().getListaBook().stream()
                    .filter(books -> books.getTitle().equalsIgnoreCase(title))
                    .findFirst()
                    .orElse(null);
            assert bookAux != null;
            return new BookDto(bookAux.getTitle(),
                    bookAux.getCountDownload(),
                    bookAux.getLanguage(), bookAux.getCopyright(),
                    Gender.getValue(bookAux.getGender()),
                    new AuthorDTO(authorAux.getFullName(), authorAux.getBirthDate(), authorAux.getDeathDate()));

        } else {
            //Author gutendex
            Author newAuthor = gutendexService.findBookByTitleGutendex(title);
            Author authorDb = authorExist(newAuthor.getFullName());


            if (authorDb != null) {
                authorDb.addBook(newAuthor.getListaBook().get(0));
                authorRepository.save(authorDb);

            } else {
                authorRepository.save(newAuthor);
            }
            var bookDto1 = newAuthor.getListaBook().get(0);
            return new BookDto(bookDto1.getTitle(), bookDto1.getCountDownload(), bookDto1.getLanguage(), bookDto1.getCopyright(), Gender.getValue(bookDto1.getGender()),
                    new AuthorDTO(newAuthor.getFullName(), newAuthor.getBirthDate(), newAuthor.getDeathDate()));


        }


    }

    private boolean checkIfExitsBook(Author author, String title) {
        for (Book b : author.getListaBook()) {
            if (b.getTitle().contains(title)) {
                return true;
            }
        }
        return false;
    }

    public Author authorExist(String fullName) {
        var author = authorRepository.findByFullName(fullName);
        return author.orElse(null);

    }

}
