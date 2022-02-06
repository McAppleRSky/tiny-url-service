package cod.nord.service.codec;

import cod.nord.repository.LinkRepository;
import cod.nord.repository.entity.Link;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CodecServiceImpl implements CodecService {

    private final LinkRepository linkRepository;
    private Map<String, String> pathUrl;
    private Map<String, String> urlPath;

    @Value("${path.codec.chars}")
    private String PATH_CHARS;
    @Value("${path.codec.length}")
    private int PATH_LENGTH;

    String generatePath() {
        char[] code = new char[PATH_LENGTH];
        for (int i = 0; i < PATH_LENGTH; i++){
            code[i] = PATH_CHARS.charAt( (int)(Math.random() * PATH_CHARS.length()) );
        }
        return String.copyValueOf(code);
    }

    @Override
    public String encode(String originUrl) {
//        Map<String, String>
                pathUrl = new HashMap<>();
//        Map<String, String>
                urlPath = new HashMap<>();
        for (Link link : linkRepository.findAll()) {
            urlPath.put(link.getUrl(), link.getPath());
            pathUrl.put(link.getPath(), link.getUrl());
        }
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
//        throw new NotImplementedException("String encode(String)");
//        return null;
    }

    @Override
    public String decode(String tinyPath) {
        throw new NotImplementedException("String decode(String)");
//        return null;
    }
}
