package com.EbookApi.apiEBook;

import com.EbookApi.apiEBook.menu.ColoursConsole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ColoursConsoleTest {
    @Test
    @DisplayName("check is colour is valid")
    public void colourValid(){
        var re=ColoursConsole.paintFont("CYAN","Hello World");
        System.out.println(re);
    }
}
