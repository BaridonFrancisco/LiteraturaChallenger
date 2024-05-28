package com.EbookApi.apiEBook.service;

import com.EbookApi.apiEBook.dto.AuthorDTO;
import com.EbookApi.apiEBook.dto.BookDto;
import com.EbookApi.apiEBook.menu.ColoursConsole;
import com.EbookApi.apiEBook.model.Author;
import com.EbookApi.apiEBook.model.Book;
import com.EbookApi.apiEBook.model.Gender;
import com.EbookApi.apiEBook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

            // autor de la base guntex
            Author newAuthor = gutendexService.findBookByTitleGutendex(title);
            if(newAuthor==null){
                System.out.println(ColoursConsole.paintFontBackground("BGYELLOW","RED","No se ha encontrado el libro solicitado"));
                return null;
            }
            try {
                System.out.println(ColoursConsole.paintFontBackground("BGPURPLE","WHITE","Buscando de otra fuente aguarde"));
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // autor de la base de datos
            Author authorDb = authorExist(newAuthor.getFullName());

            System.out.println(authorDb);
            System.out.println("Separacion--------");
            System.out.println(newAuthor);
            System.out.println("---");
            if (authorDb != null) {
                newAuthor.setId(authorDb.getId());
                List<Book> bookRef = new ArrayList<>(authorDb.getListaBook());
                bookRef.add(newAuthor.getListaBook().get(0));
                authorDb.getListaBook().addAll(newAuthor.getListaBook());
                authorRepository.save(authorDb);

            } else {
                authorRepository.save(newAuthor);
            }
            System.out.println("fin");
            var bookDto1 = newAuthor.getListaBook().get(0);
            return new BookDto(bookDto1.getTitle(), bookDto1.getCountDownload(), bookDto1.getLanguage(), bookDto1.getCopyright(), Gender.getValue(bookDto1.getGender()),
                    new AuthorDTO(newAuthor.getFullName(), newAuthor.getBirthDate(), newAuthor.getDeathDate()));


        }


    }
    

    public Author authorExist(String fullName) {
        Optional<Author> author = authorRepository.findByFullName(fullName);
        return author.orElse(null);

    }
    public List<AuthorDTO>allAuthors(){
        return authorRepository.findAll().stream()
                .map(author ->new AuthorDTO(author.getFullName(),author.getBirthDate(),author.getDeathDate()))
                .collect(Collectors.toList());
    }

    public List<BookDto> allBooks() {
        return mapBookToBookDTO(authorRepository.allBooks());
    }

    public List<BookDto> top5Books() {
        return mapBookToBookDTO(authorRepository.top5Books());
    }
    public List<BookDto>listByTopic(Gender gender){
        return mapBookToBookDTO(authorRepository.listByTopic(gender));
    }
    public List<BookDto>listByLanguage(String lan){
        return mapBookToBookDTO(authorRepository.listbyLanguage(lan));
    }

    private List<BookDto> mapBookToBookDTO(List<Book>listBooks){
        return listBooks.stream()
                .map(book->new BookDto(book.getTitle(),book.getCountDownload(),book.getLanguage(),book.getCopyright(),book.getGender().toString(),
                        new AuthorDTO(book.getAuthor().getFullName(),book.getAuthor().getBirthDate(),book.getAuthor().getDeathDate())))
                .collect(Collectors.toList());
    }

}
