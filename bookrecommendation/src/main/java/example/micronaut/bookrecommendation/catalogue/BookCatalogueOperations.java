package example.micronaut.bookrecommendation.catalogue;

import example.micronaut.bookrecommendation.Book;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface BookCatalogueOperations {
    Flowable<Book> findAll();

    Single<Book> get(String isbn);
}