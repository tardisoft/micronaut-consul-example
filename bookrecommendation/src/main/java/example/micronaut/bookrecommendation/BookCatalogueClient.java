package example.micronaut.bookrecommendation;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.Client;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Client(id = "bookcatalogue")
public interface BookCatalogueClient {

    @Get("/books")
    Flowable<Book> findAll();

    @Get("/books/{isbn}")
    Single<Book> get(String isbn);
}
