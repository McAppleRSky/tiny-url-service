package cod.nord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

    // https://habr.com/ru/post/447322/
    // https://habr.com/ru/post/545610/
    // https://habr.com/ru/post/340146/
    // https://java-master.com/spring-security-%D1%81-%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E-jwt-%D1%82%D0%BE%D0%BA%D0%B5%D0%BD%D0%B0/
    // https://www.baeldung.com/java-json-web-tokens-jjwt

    // https://struchkov.dev/blog/jwt-implementation-in-spring/
    // https://github.com/Example-uPagge/jwt-server-spring
    // https://www.marcobehler.com/guides/spring-mvc
    // https://javascopes.com/spring-annotations-requestmapping-and-its-variants-e8cf47fd/
