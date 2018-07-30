package example.micronaut.bookcatalogue;

import io.micronaut.context.env.Environment;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller("/books")
public class BooksController {

    private Environment environment;

    public BooksController(Environment environment) {
        this.environment = environment;
    }

    static final List<Book> books = Arrays.asList(
            new Book("1491950358", "Building Microservices"),
            new Book("1680502395", "Release It!"),
            new Book("0321601912", "Continuous Delivery"),
            new Book("1", "Micronaut Prime"),
            new Book("2", "Micronaut Examples")
    );

    @Get("/")
    List<Book> list() {
        List<Book> localBooks = new ArrayList<>(books);
        if (environment.getActiveNames().contains("slave")) {
            localBooks.add(new Book("3", "Extra Book"));
        }
        return localBooks;
    }

    @Get("/{isbn}")
    Book book(String isbn) {
        return books.stream().filter(book -> book.getIsbn().equalsIgnoreCase(isbn)).findFirst().orElse(null);
    }
}
