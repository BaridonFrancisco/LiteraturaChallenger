package com.EbookApi.apiEBook;


import com.EbookApi.apiEBook.repository.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiEBookApplication implements CommandLineRunner {



	public static void main(String[] args) {

		SpringApplication.run(ApiEBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Menu menu=new Menu();
		menu.startAPIMenu();*/
	}
}
