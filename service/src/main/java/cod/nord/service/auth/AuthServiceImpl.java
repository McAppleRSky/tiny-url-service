package cod.nord.service.auth;

import cod.nord.repository.entity.Oper;
import cod.nord.service.OperService;
import cod.nord.service.auth.model.JwtRequest;
import cod.nord.service.auth.model.JwtResponse;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final OperService operService;
    private final JwtProvider jwtProvider;
    private final Map<String, String> mapLoginRefresh = new ConcurrentHashMap<>();

    /*@Override public Optional<User> getByLogin(@NonNull String login) { userService.getByLogin(login);
        throw new NotImplementedException("Optional<User> getByLogin(String login)"); }*/

    @Override
    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final Oper oper = operService.getByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("User not found"));
        if (oper.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(oper);
            final String refreshToken = jwtProvider.generateRefreshToken(oper);
            mapLoginRefresh.put(oper.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Invalid password");
        }
    }

    @Override
    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = mapLoginRefresh.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Oper oper = operService.getByLogin(login)
                        .orElseThrow(() -> new AuthException("User not found"));
                final String accessToken = jwtProvider.generateAccessToken(oper);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = mapLoginRefresh.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Oper oper = operService.getByLogin(login)
                        .orElseThrow(() -> new AuthException("User not found"));
                final String accessToken = jwtProvider.generateAccessToken(oper);
                final String newRefreshToken = jwtProvider.generateRefreshToken(oper);
                mapLoginRefresh.put(oper.getLogin(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid JWT token");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
