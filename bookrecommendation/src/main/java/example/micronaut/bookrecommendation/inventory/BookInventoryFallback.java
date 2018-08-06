package example.micronaut.bookrecommendation.inventory;

import io.micronaut.retry.annotation.Fallback;
import io.reactivex.Maybe;

import javax.validation.constraints.NotBlank;

@Fallback
public class BookInventoryFallback implements BookInventoryOperations {

    @Override
    public Maybe<Integer> stock(@NotBlank String isbn) {
        return Maybe.just(-1);
    }
}
