package cod.nord.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class LinkResponse {

    private final Integer id;
    private final String path;
    private final String url;
    private final LocalDateTime expire;
    private final Integer followCount;
    private final Integer followUniqueCount;

}
