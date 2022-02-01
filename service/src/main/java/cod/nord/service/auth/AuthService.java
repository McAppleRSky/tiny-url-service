package cod.nord.service.auth;

import cod.nord.service.auth.model.JwtRequest;
import cod.nord.service.auth.model.JwtResponse;

public interface AuthService {

//    Optional<User> getByLogin(String login);

    JwtResponse login(JwtRequest authRequest) ;

    JwtResponse getAccessToken(String refreshToken) ;

    JwtResponse refresh(String refreshToken) ;

    JwtAuthentication getAuthInfo();

}
