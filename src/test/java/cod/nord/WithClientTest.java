package cod.nord;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;

@SuppressWarnings("deprecation")
public class WithClientTest {

    private static final String HOST = "http://localhost:8080";
    private static final String RES = "/api/0.0.1";
    private static final String POSTS_API_TEMP = HOST + RES;    private static final int WAIT_SPRING_UP = 30_000;

    @Test
    void first() {
        Thread server = new Thread(() -> Application.main(new String[]{}));
        server.start();
        try {
            Thread.sleep(WAIT_SPRING_UP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();

        Queue queue = new Queue(QUEUE_NAME, new _Rand()._messageSome(1, 10));
        int messageCount = queue.getMassages().size();
        ObjectMapper mapper = new ObjectMapper();
        String body = null;
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            body = mapper.writeValueAsString(queue);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("content-type", "application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (java.net.ConnectException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotNull(response);
        int EXPECTED_HTTP_STATUS = 200;
        assertEquals(EXPECTED_HTTP_STATUS, response.statusCode());
        String responseBody = response.body();
        server.stop(); // deprecated
    }

}
