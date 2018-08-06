package example.micronaut.bookrecommendation.inventory;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.Client;
import io.micronaut.retry.annotation.CircuitBreaker;
import io.reactivex.Maybe;

import javax.validation.constraints.NotBlank;

@Client(id = "bookinventory")
@CircuitBreaker(delay = "1ms")
public interface BookInventoryClient extends BookInventoryOperations {

    @Get("/books/stock/{isbn}")
    Maybe<Integer> stock(@NotBlank String isbn);
}
