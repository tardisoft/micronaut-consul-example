package example.micronaut.bookrecommendation;

import example.micronaut.bookrecommendation.catalogue.BookCatalogueClient;
import example.micronaut.bookrecommendation.inventory.BookInventoryClient;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.retry.annotation.CircuitBreaker;
import io.micronaut.retry.annotation.Fallback;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Controller("/books")
public class BookController {

    private final BookCatalogueClient bookCatalogueOperations;
    private final BookInventoryClient bookInventoryOperations;

    public BookController(BookCatalogueClient bookCatalogueOperations,
                          BookInventoryClient bookInventoryOperations) {
        this.bookCatalogueOperations = bookCatalogueOperations;
        this.bookInventoryOperations = bookInventoryOperations;
    }


    @Get()
    @CircuitBreaker
    public Flowable<Book> books() {
        return bookCatalogueOperations.findAll()
                .flatMapMaybe(b -> bookInventoryOperations.stock(b.getIsbn())
                        .defaultIfEmpty(0)
                        .map(stock -> {
                            b.setStock(stock);
                            return b;
                        })
                ).map(book -> book);
    }

    @Get("/resource")
    public Flowable<Resource<Book>> bookResources() {
        return bookCatalogueOperations.findAll()
                .flatMapMaybe(b -> bookInventoryOperations.stock(b.getIsbn())
                        .defaultIfEmpty(0)
                        .map(stock -> {
                            b.setStock(stock);
                            return b;
                        })
                ).map(book -> new ResourceBuilder<>(book)
                        .withLink("inventory", "/" + book.getIsbn() + "/inventory")
                        .withLink("self", "/" + book.getIsbn())
                        .build());
    }

    @Get("/{isbn}/inventory")
    public Flowable<Integer> inventory(String isbn) {
        return bookInventoryOperations.stock(isbn).defaultIfEmpty(0).toFlowable();
    }

    @Get("/{isbn}")
    public Single<Book> book(String isbn) {
        return bookCatalogueOperations.get(isbn).flatMapMaybe(b -> bookInventoryOperations.stock(b.getIsbn())
                .defaultIfEmpty(0)
                .map(stock -> {
                    b.setStock(stock);
                    return b;
                })
        ).toSingle();
    }

}
