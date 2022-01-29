package cod.nord.service.auth.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class RefreshJwtRequest {
    private // final
        String refreshToken;
}
