package cod.nord.service.auth.model;

import lombok.*;

@Getter @Setter
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    private // final
        String login;
    private // final
        String password;

}
