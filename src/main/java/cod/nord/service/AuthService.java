package cod.nord.service;

import cod.nord.service.model.JwtRequest;
import cod.nord.service.model.JwtResponse;

import javax.security.auth.message.AuthException;

public interface AuthService {

//    Optional<User> getByLogin(String login);

    JwtResponse login(JwtRequest authRequest) ;

    JwtResponse getAccessToken(String refreshToken) ;

    JwtResponse refresh(String refreshToken) ;

    JwtAuthentication getAuthInfo();

}
