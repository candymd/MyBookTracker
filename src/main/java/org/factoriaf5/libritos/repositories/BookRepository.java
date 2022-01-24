package org.factoriaf5.libritos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository <Book, Long > {
    List<Book> findByRating(Integer rating);
    @Query("select s from Book s")
    List<Book> findAllByFinished(Sort finished);
}
