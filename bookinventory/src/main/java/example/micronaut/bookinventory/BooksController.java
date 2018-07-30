package example.micronaut.bookinventory;

import io.micronaut.context.env.Environment;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.validation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Validated
@Controller("/books")
public class BooksController {

    private Environment environment;

    public BooksController(Environment environment) {
        this.environment = environment;
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/stock/{isbn}")
    public Integer stock(@NotBlank String isbn) {
        Optional<BookInventory> bookInventoryOptional = bookInventoryByIsbn(isbn);
        if (!bookInventoryOptional.isPresent()) {
            return null;
        }
        BookInventory bookInventory = bookInventoryOptional.get();
        return bookInventory.getStock();
    }

    private Optional<BookInventory> bookInventoryByIsbn(String isbn) {
        switch (isbn) {
            case "1491950358":
                return Optional.of(new BookInventory(isbn, 4));
            case "1680502395":
                return Optional.of(new BookInventory(isbn, 0));
            case "1":
                if (environment.getActiveNames().contains("slave")) {
                    return Optional.of(new BookInventory(isbn, 99));
                } else {
                    return Optional.of(new BookInventory(isbn, 100));
                }
        }
        return Optional.empty();
    }
}
