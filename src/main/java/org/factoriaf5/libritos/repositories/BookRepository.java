package org.factoriaf5.libritos.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository <Book, Long > {
    List<Book> findAllByFinished(String finished);

}
