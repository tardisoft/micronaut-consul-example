package example.micronaut.bookrecommendation;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

import java.net.URI;

@Controller("/")
public class IndexController {

    @Produces(MediaType.TEXT_HTML)
    @Get("/")
    HttpResponse index() {
        return HttpResponse.redirect(URI.create("/index.html"));
    }
}
