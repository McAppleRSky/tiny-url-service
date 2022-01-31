package cod.nord;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import cod.nord.repository.entity.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SuppressWarnings("deprecation")
public class WithClientTest {

    private final String HOST = "http://localhost:8080";
    private final String HOST_API = HOST + "/api/0.0.1";
    private final int WAIT_SPRING_UP = 30_000;
    private Thread server;
    private HttpClient client;

    @BeforeEach
    void prepare() {
        server = new Thread(() -> Service.main(new String[]{}));
        server.start();
        try {
            Thread.sleep(WAIT_SPRING_UP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client = HttpClient.newHttpClient();
    }

    @Test
    void getHostTest() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("content-type", "text/html;charset=utf-8")
                .uri(URI.create(HOST + "/" + randomAlphabetic(3)))
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
        int EXPECTED_HTTP_STATUS = 302;
        assertEquals(EXPECTED_HTTP_STATUS, response.statusCode());
        String responseBody = response.body();
    }

    @Test
    void getHostRootEmptyTest() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("content-type", "text/html;charset=utf-8")
                .uri(URI.create(HOST + "/"))
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
        int EXPECTED_HTTP_STATUS = 302;
        assertEquals(EXPECTED_HTTP_STATUS, response.statusCode());
        String responseBody = response.body();
    }

    @Test
    void getUsersTest() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("content-type", "application/json, */*; q=0.01;charset=utf-8")
                .uri(URI.create(HOST_API + "/user"))
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
    }

    @Test
    void getUserTest() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("content-type", "application/json;charset=utf-8")
                .uri(URI.create(HOST_API + "/user/" + Integer.MAX_VALUE))
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
    }

    @Test
    void postUserTest() {
        String login = randomAlphabetic(3);
        String name = randomAlphabetic(3);
        String password = randomAlphabetic(8);
        String email = randomAlphabetic(3)
                + "@" + randomAlphabetic(3)
                + "." + randomAlphabetic(2);
        Map<String, String> map = new HashMap<>();
        map.put("login", login);
        map.put("password", password);
        map.put("name", name);
        map.put("email", email);
        map.put("roles", Role.values()[nextInt(0, Role.values().length)].name());
        ObjectMapper mapper = new ObjectMapper();
        String body = null;
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            body = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("content-type", "application/json;charset=utf-8")
                .uri(URI.create(HOST_API + "/user"))
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
    }

    @Test
    void patchUserTest() {
        String login = randomAlphabetic(3);
        String name = randomAlphabetic(3);
        String password = randomAlphabetic(8);
        String email = randomAlphabetic(3)
                + "@" + randomAlphabetic(3)
                + "." + randomAlphabetic(2);
        Map<String, String> map = new HashMap<>();
        map.put("login", login);
        map.put("password", password);
        map.put("name", name);
        map.put("email", email);
        map.put("roles", Role.values()[nextInt(0, Role.values().length)].name());
        ObjectMapper mapper = new ObjectMapper();
        String body = null;
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            body = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .method("PATCH", HttpRequest.BodyPublishers.ofString(body))
                .header("content-type", "application/json;charset=utf-8")
                .uri(URI.create(HOST_API + "/user/" + (Integer.MAX_VALUE-1)))
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
    }

    @Test
    void deleteUserTest() {
        HttpRequest request = HttpRequest.newBuilder()
                .method("DELETE", HttpRequest.BodyPublishers.noBody())
                .header("content-type", "application/json;charset=utf-8")
                .uri(URI.create(HOST_API + "/user/" + (Integer.MAX_VALUE-2)))
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
    }

    @Test
    void postLoginTest() {
        String login = randomAlphabetic(3);
        String password = randomAlphabetic(8);

        Map<String, String> map = new HashMap<>();
        map.put("login", login);
        map.put("password", password);
        ObjectMapper mapper = new ObjectMapper();
        String body = null;
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            body = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("content-type", "application/json;charset=utf-8")
                .uri(URI.create(HOST_API + "/login"))
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
    }

    @Test
    void postRefresh_getAccessTokenTest() {
        String token = randomAlphabetic(80);

        Map<String, String> map = new HashMap<>();
        map.put("refreshToken", token);
        ObjectMapper mapper = new ObjectMapper();
        String body = null;
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            body = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("content-type", "application/json;charset=utf-8")
                .uri(URI.create(HOST_API + "/token"))
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
    }

    @Test
    void postNewRefreshToken() {
        String token = randomAlphabetic(80);

        Map<String, String> map = new HashMap<>();
        map.put("refreshToken", token);
        ObjectMapper mapper = new ObjectMapper();
        String body = null;
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            body = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("content-type", "application/json;charset=utf-8")
                .uri(URI.create(HOST_API + "/refresh"))
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
    }

    @AfterEach
    void clean() {
        client = null;
        server.stop(); // deprecated
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
