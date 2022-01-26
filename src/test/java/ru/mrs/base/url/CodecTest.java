package ru.mrs.base.url;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodecTest {

    private Codec codec;

    @Test
    public void testEncode() {
        Map<String, String> urlPath = new HashMap<>();
        Map<String, String> pathUrl = new HashMap<>();
        codec = new CodecImpl(urlPath, pathUrl);
        String randomUrl = randomUrl();
        String expected = codec.encode(randomUrl);
        String actual = codec.encode(randomUrl);
        assertEquals(expected, actual);
        assertEquals(randomUrl, codec.decode(actual));
        System.out.println();
    }

    String randomUrl() {
        return new StringBuilder("https://")
                .append(randomAlphabetic(4))
                .append(".")
                .append(randomAlphabetic(3))
                .append("/")
                .append(randomAlphabetic(5))
                .toString();
    }

    @Test
    void randomUrlTest() {
        assertDoesNotThrow(() -> {
            new URL(randomUrl());
        });
    }

}
