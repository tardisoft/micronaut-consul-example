package example.micronaut.bookrecommendation.catalogue;

import example.micronaut.bookrecommendation.Book;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.Client;
import io.micronaut.retry.annotation.CircuitBreaker;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Client(id = "bookcatalogue")
@CircuitBreaker(delay = "1ms")
public interface BookCatalogueClient extends BookCatalogueOperations {

    @Get("/books")
    Flowable<Book> findAll();

    @Get("/books/{isbn}")
    Single<Book> get(String isbn);
}
