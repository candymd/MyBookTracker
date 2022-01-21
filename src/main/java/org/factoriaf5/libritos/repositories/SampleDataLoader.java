package org.factoriaf5.libritos.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SampleDataLoader {

    private BookRepository bookRepository;

    @Autowired
    public SampleDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void loadSampleData() {
        bookRepository.saveAll(List.of(
                new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",5, "March-2021", "May-2021", "it was very nice!", true),
                new Book("Little Women", "Louisa M. Alcott", "Novel", "https://images-na.ssl-images-amazon.com/images/I/71DyZHgCDiL.jpg", 4, "2021", "2021", "quite good" ,true),
                new Book("Los desposeídos", "Ursula K. Leguin", "Fantasy", "https://www.planetadelibros.com/usuaris/libros/fotos/320/m_libros/portada_los-desposeidos_ursula-k-le-guin_202007201548.jpg", 4, "2021", "2021", "I didn't finish it", false),
                new Book("Lean Software Development", "Mary Poppendieck", "Software", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1347395427l/6743843.jpg", 3, "2021", "2021", "Could be better :/", false),
                new Book("Women, Race and Class", "Angela Y. Davis", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81nplAuw2CL.jpg", 2, "2021", "2021", "Interesting", true),
                new Book("Object Design", "Rebecca Wirfs-Brock", "Software", "https://images-na.ssl-images-amazon.com/images/I/A17f9QsXaXL.jpg", 5, "2021", "2022", "A masterpiece! Absolutely loved it!!!",true)
        ));
    }
}