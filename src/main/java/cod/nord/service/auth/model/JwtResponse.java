package cod.nord.service.auth.model;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class JwtResponse {

    private final String type = "Bearer";
    private // final
        String accessToken;
    private // final
        String refreshToken;
}
