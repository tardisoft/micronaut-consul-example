package example.micronaut.bookrecommendation;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.Client;
import io.reactivex.Maybe;

import javax.validation.constraints.NotBlank;

@Client(id = "bookinventory")
public interface BookInventoryClient {

    @Get("/books/stock/{isbn}")
    Maybe<Integer> stock(@NotBlank String isbn);
}
