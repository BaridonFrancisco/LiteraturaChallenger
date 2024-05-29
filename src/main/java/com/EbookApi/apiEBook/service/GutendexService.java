package com.EbookApi.apiEBook.service;
import com.EbookApi.apiEBook.dto.AuthorDTO;
import com.EbookApi.apiEBook.model.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class
GutendexService {

    final String urlBase = "https://gutendex.com/books?";
    TransformData transformData = new TransformData();

    public Author findAuthorByTitle(String title) throws IOException, URISyntaxException {
        String url = urlBase + "search=" + title.replace(" ", "%20");
        DateResult result = transformData.deserializarEntity(new URL(url), DateResult.class);
        System.out.println(result.results());
        var resultado = result.results().stream()
                .collect(Collectors.toMap(a -> getAuthor(a.listaAutores())
                        , this::getBook));
        var entryAuthor = resultado.entrySet().stream()
                .max(Map.Entry.comparingByValue(Comparator.comparing(Book::getCountDownload)))
                .map(entry -> {
                    Author author = entry.getKey();
                    Book book = entry.getValue();
                    List<Book> listBook = new ArrayList<>();
                    listBook.add(book);
                    author.setListaBook(listBook);
                    book.setAuthor(author);
                    return entry;
                });
        return entryAuthor.map(Map.Entry::getKey)
                .orElse(null);

    }

    public Author searchAuthorGutendex(String fullName) throws IOException, URISyntaxException {
        String url = urlBase + "search=" + fullName.replace(" ", "%20");
        DateResult result = transformData.deserializarEntity(new URL(url), DateResult.class);
      return result.results().stream()
                .flatMap(dataBook->dataBook.listaAutores().stream().parallel()
                        .filter(dataAuthor->dataAuthor.name().equalsIgnoreCase(fullName))
                        .map(dateAuthors ->new Author(dateAuthors.name(),Integer.parseInt(dateAuthors.birthDate()),Integer.parseInt(dateAuthors.deathYear()),new ArrayList<>())))
               .findAny().orElse(null);


    }

    //transforma un author
    private Author getAuthor(List<DateAuthors> listAuthors) {
        return listAuthors.stream()
                .findFirst()
                .map(author -> new Author(author.name(),
                        LocalDate.of(Integer.parseInt(author.birthDate()), 1, 1).getYear(),
                        LocalDate.of(Integer.parseInt(author.deathYear()), 1, 1).getYear(),
                        null)).orElse(null);
    }

    //transforma un dateBook a un Book
    private Book getBook(DateBook dateBook) {
        return new Book(null, dateBook.copyright()
                , getGender(dateBook.gender()),
                Arrays.toString(dateBook.languages()),
                dateBook.downloadCount(), dateBook.title());
    }

    //Obtiene un genero aleatorio de gutendex
    private Gender getGender(String[] genders) {
        var genderBook = Arrays.stream(genders)
                .flatMap(gender -> Arrays.stream(Gender.values())
                        .filter(genAux -> gender.contains(Gender.getValue(genAux))))
                .distinct()
                .collect(Collectors.toList());
        if(genderBook.isEmpty()){
            genderBook.add(Gender.UNKNOWN);
        }
        int ramdomIndex = new Random().nextInt(0, genderBook.size());
        return genderBook.get(ramdomIndex);

    }


}
