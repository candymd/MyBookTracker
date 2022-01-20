package org.factoriaf5.libritos;

import org.factoriaf5.libritos.repositories.Book;
import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class BookTest {

    @Test
    void bookHasTittle() {
        Book book = new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",5, "March-2021", "May-2021", "",true);
        assertThat(book.getTitle(), equalTo("Una habitación propia"));
    }

     @Test
    void bookHasAuthor() {
        Book book = new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",5, "March-2021", "May-2021", "",true);
        assertThat(book.getAuthor(), equalTo("Virginia Woolf"));
    }

     @Test
    void bookHasCategory() {
        Book book = new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",5, "March-2021", "May-2021", "",true);
        assertThat(book.getCategory(), equalTo("Essay"));
    }

     @Test
    void bookHasImage() {
        Book book = new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",5, "March-2021", "May-2021","", true);
        assertThat(book.getImageURL(), equalTo("https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg"));
    }

/*     @Test
    void getRatingReturnsNumbersOnlyBetweenZeroAndFive() {
        Book book1 = new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",-1, "March-2021", "May-2021","", true);
        Book book2 = new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",8, "March-2021", "May-2021","", true);
        Book book3 = new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",3, "March-2021", "May-2021","", true);
        Book book4 = new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",0, "March-2021", "May-2021","", true);
        assertThat(book1.getRating(), equalTo(0));
        assertThat(book2.getRating(), equalTo(5));
        assertThat(book3.getRating(), equalTo(3));
        assertThat(book4.getRating(), equalTo(0));
    }*/

      @Test
    void bookHasStartingDateAndFinishDate() {
        Book book = new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",5, "March-2021", "May-2021","", true);
        assertThat(book.getStartingDate(), equalTo("March-2021"));
        assertThat(book.getFinishDate(), equalTo("May-2021"));
    }

      @Test
    void checksIfBookIsFinished() {
        Book book = new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",5, "March-2021", "May-2021","", true);
        assertThat(book.isFinished(), equalTo(true));
    }



}