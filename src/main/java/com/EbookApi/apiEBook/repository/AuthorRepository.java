package com.EbookApi.apiEBook.repository;
import com.EbookApi.apiEBook.model.Author;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    @Query("SELECT a FROM Author a JOIN a.listaBook lb WHERE lb.title LIKE :bookTitle")
    Optional<Author>findByBook(@Param("bookTitle")String bookTitle);

    Optional<Author>findByFullName(String name);



}
