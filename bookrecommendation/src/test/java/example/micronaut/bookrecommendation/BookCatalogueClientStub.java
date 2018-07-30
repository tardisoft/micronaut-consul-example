package example.micronaut.bookrecommendation;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.retry.annotation.Fallback;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Singleton;

@Requires(env = Environment.TEST)
@Fallback
@Singleton
public class BookCatalogueClientStub implements BookCatalogueClient {

    @Override
    public Flowable<Book> findAll() {
        Book buildingMicroservices = new Book("1491950358", "Building Microservices");
        Book releaseIt = new Book("1680502395", "Release It!");
        return Flowable.just(buildingMicroservices, releaseIt);
    }

    @Override
    public Single<Book> get(String isbn) {
        return Single.just(new Book(isbn, "Building Microservices"));
    }
}
