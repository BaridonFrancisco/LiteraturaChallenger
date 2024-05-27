package com.EbookApi.apiEBook.menu;

import com.EbookApi.apiEBook.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Scanner;
@Component
public class Menu {
    Scanner scanner=new Scanner(System.in);
    @Autowired
    AuthorService authorService;

    public Menu(AuthorService authorService) {
        this.authorService = authorService;
    }

    public void startAPIMenu() throws IOException, URISyntaxException {
        int op=-1;
        System.out.println("Bienvenidos al menu de API ebook");
        do{
            try {
                System.out.println("""
                        *****Menu*****
                        1.Listar todos libros disponibles
                        2.Buscar libro por titulo
                        3.Listar autores registrados
                        4.Buscar libro por topico
                        5.Listar top 5 libros
                        6.Listar por anio
                        7.Buscar libros por idiomas
                        0.Salir""");
               switch ( op = scanner.nextInt()){
                   case 1:
                       var allBooks=authorService.allBooks();
                       if(allBooks==null || allBooks.isEmpty()){
                           System.out.println("No hay libros cargados");
                           break;
                       }
                       allBooks.forEach(System.out::println);
                       break;
                   case 2:
                       var s=authorService.findByTitle("Symbolic Logic");
                       System.out.println("libroDto resultado->"+s);
                       System.out.println(ColoursConsole.paintFontBackground("BGGREEN","RED","Archivo guardado con exito"));
                       break;
                   case 3:
                       System.out.println(ColoursConsole.paintFont("yellow","NOTA los autores se registran buscando libro no existentes"));
                       var allAuthors=authorService.allAuthors();
                       System.out.println(allAuthors);
                       break;
                   case 4:
                       break;
                   case 5:
                       break;
                   case 6:
                       break;
                   case 7:
                       break;
                   case 0:
                       System.out.println("Saliendo...");
                       Thread.sleep(2000);
                       break;
                   default:
                       System.out.println("No ha seleccionado una opcion correcta");


               }
            }catch (InputMismatchException e){
                System.out.println("no ha ingresado una opcion valida intentelo nuevamente");
            }catch (InterruptedException e){
                System.out.println("ups");
            }
        }while(op!=0);
        scanner.close();
    }
}
