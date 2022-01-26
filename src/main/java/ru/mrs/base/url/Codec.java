package ru.mrs.base.url;

public interface Codec {

    String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    int SHOT_LENGTH = 6;

    String encode(String originUrl);

    String decode(String tinyPath);

}
