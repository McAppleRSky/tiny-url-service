package cod.nord.repository;

import javax.servlet.http.HttpServletResponse;

public interface JwtTokenRepository {
    void clearToken(HttpServletResponse response);
}
