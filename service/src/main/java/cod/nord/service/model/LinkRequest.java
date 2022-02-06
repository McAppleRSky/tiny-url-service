package cod.nord.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class LinkRequest {

//    private final String path;
    private final String url;
    private final LocalDateTime expire;
//    private final Integer followCount;
//    private final Integer followUniqueCount;

}
