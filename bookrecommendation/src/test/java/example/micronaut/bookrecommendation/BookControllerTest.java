package example.micronaut.bookrecommendation;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxStreamingHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.reactivex.Flowable;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookControllerTest {
    private static EmbeddedServer server;
    private static RxStreamingHttpClient client;

    @BeforeClass // <1>
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server
                .getApplicationContext()
                .createBean(RxStreamingHttpClient.class, server.getURL());
    }

    @AfterClass // <1>
    public static void stopServer() {
        if (server != null) {
            server.stop();
        }
        if (client != null) {
            client.stop();
        }
    }

    @Test
    public void testRetrieveBooks() throws Exception {
        Flowable<List> books = client.jsonStream(HttpRequest.GET("/books"), List.class);
        assertEquals(books.toList().blockingGet().size(), 1);
        assertNotNull(books.toList().blockingGet().get(0));
    }
}
