package example.micronaut.bookrecommendation.catalogue;

import example.micronaut.bookrecommendation.Book;
import io.micronaut.http.annotation.Get;
import io.micronaut.retry.annotation.Fallback;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Fallback
public class BookCatalogueFallback implements BookCatalogueOperations {

    public Flowable<Book> findAll() {
        return Flowable.just(new Book("0", "Fallback Book"));
    }

    @Get("/books/{isbn}")
    public Single<Book> get(String isbn) {
        return Single.just(new Book("0", "Fallback Book"));
    }
}
