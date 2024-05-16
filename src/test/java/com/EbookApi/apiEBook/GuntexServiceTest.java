package com.EbookApi.apiEBook;

import com.EbookApi.apiEBook.model.*;
import com.EbookApi.apiEBook.service.TransformData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootTest
public class GuntexServiceTest {
    final String urlBase = "https://gutendex.com/books?";
    TransformData transformData = new TransformData();

    @Test
    @DisplayName("test authors")
    public void getListAuthorsTest() throws IOException, URISyntaxException {
        try {
            String title = "Great Expectations";
            String url = urlBase + "search=" + title.replace(" ", "%20");
            var result = transformData.deserializarEntity(new URL(url), DateResult.class);
            var resultado = result.results().stream()
                    .collect(Collectors.toMap(a -> getAuthor(a.listaAutores())
                            , this::getBook));
            var ere=resultado.entrySet().stream()
                    .max(Map.Entry.comparingByValue(Comparator.comparing(Book::getCountDownload)))
                    .map(entry->{
                        Author author=entry.getKey();
                        Book book=entry.getValue();
                        List<Book>listBook=new ArrayList<>();
                        listBook.add(book);
                        author.setListaBook(listBook);
                        book.setAuthor(author);
                        return entry;
                    }).get();
            System.out.println(ere.getValue().getAuthor());

           // System.out.println(resultado);
           /*     .distinct()
                .sorted(Comparator.comparing(DateBook::downloadCount))
               .collect(Collectors.groupingBy(DateBook::listaAutores, Collectors.mapping(this::getBook, Collectors.toSet())));
        resultado.forEach((k,v)-> System.out.println("key "+k+"value "+v));
        resultado.entrySet().stream().forEach((entry)->{

        });*/
            //  .flatMap(book->book.listaAutores().stream()
            //                        .map(listaAutores->new Author(listaAutores.name(), LocalDate.parse(listaAutores.birthDate()),LocalDate.parse(listaAutores.deathYear()),new ArrayList<>())
            //                        ));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Book getBook(DateBook dateBook) {
        Arrays.stream(dateBook.genero())
                .filter(gender->gender.contains(""))
       /* Author author=dateBook.listaAutores().stream()
                .findFirst()
                .map(a->new Author(null,LocalDate.parse(a.birthDate()),LocalDate.parse(a.deathYear()),new ArrayList<>())).get();*/
        return new Book(null, dateBook.copyright()
                , null,
                Arrays.toString(dateBook.languages()),
                dateBook.downloadCount(), dateBook.title());
    }

    private Author getAuthor(List<DateAuthors> listAuthors) {
        return listAuthors.stream()
                .findFirst()
                .map(author -> new Author(author.name(),
                        LocalDate.of(Integer.parseInt(author.birthDate()), 1, 1).getYear(),
                        LocalDate.of(Integer.parseInt(author.deathYear()), 1, 1).getYear(),
                        null))
                .get();
    }

}
