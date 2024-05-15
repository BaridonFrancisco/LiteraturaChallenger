package com.EbookApi.apiEBook.menu;

import com.EbookApi.apiEBook.service.AuthorService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner scanner=new Scanner(System.in);

    AuthorService authorService;



    public void startAPIMenu(){
        int op=0;
        System.out.println("Bienvenidos al menu de API ebook");
        do{
            try {
                System.out.println("""
                        *****Menu*****
                        1.Listar todos libros disponibles
                        2.Buscar libro por titulo
                        3.Listar autores disponibles
                        4.Buscar libro por topico
                        5.Listar top 5 libros
                        6.Listar por anio
                        7.Buscar libros por idiomas""");
               switch ( op = scanner.nextInt()){
                   case 1:
                       break;
                   case 2:
                       break;
                   case 3:
                       break;
                   case 4:
                       break;
                   case 5:
                       break;
                   case 6:
                       break;
                   case 7:
                       break;
                   default:
                       break;

               }

            }catch (InputMismatchException e){

            }
        }while(op!=-1);

    }
}
