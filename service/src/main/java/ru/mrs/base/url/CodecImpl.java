package ru.mrs.base.url;

import java.util.HashMap;
import java.util.Map;

public class CodecImpl implements Codec {

    private final Map<String, String> urlPath;
    private final Map<String, String> pathUrl;

    public CodecImpl(Map<String, String> urlPath, Map<String, String> pathUrl) {
        this.urlPath = urlPath;
        this.pathUrl = pathUrl;
    }

    public String generatePath() {
        char[] code = new char[SHOT_LENGTH];
        for (int i = 0; i < SHOT_LENGTH; i++){
            code[i] = CHARS.charAt( (int)(Math.random() * CHARS.length()) );
        }
        return String.copyValueOf(code);
    }

    @Override
    public String encode(String originUrl) {
        String existPath = urlPath.get(originUrl);
        String newPath;
        if (existPath!=null) {
            return existPath;
        } else {
            String tryPath;
            do {
                tryPath = generatePath();
            } while (pathUrl.containsKey(tryPath));
            newPath = tryPath;
        }
        urlPath.put(originUrl, newPath);
        pathUrl.put(newPath, originUrl);
        return newPath;
    }

    @Override
    public String decode(String tinyPath) {
        return pathUrl.get(tinyPath);
    }

}
