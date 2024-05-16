package com.EbookApi.apiEBook.repository;
import com.EbookApi.apiEBook.model.Author;
import com.EbookApi.apiEBook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author,Long> {

    List<Author>findAll();

}
