package com.EbookApi.apiEBook.service;
import com.EbookApi.apiEBook.model.DateBook;
import com.EbookApi.apiEBook.model.DateResult;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class GutendexService {

    final String urlBase="https://gutendex.com/books?";
    TransformData transformData=new TransformData();
    public DateBook findBookByTitleGutendex(String title) throws IOException, URISyntaxException {
        String url=urlBase+"?search="+title.replace(" ","%20");
        var result=transformData.deserializarEntity(new URL(url), DateResult.class);
        return null;




    }
}
