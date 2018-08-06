package example.micronaut.bookrecommendation.inventory;

import io.reactivex.Maybe;

import javax.validation.constraints.NotBlank;

public interface BookInventoryOperations {
    Maybe<Integer> stock(@NotBlank String isbn);
}