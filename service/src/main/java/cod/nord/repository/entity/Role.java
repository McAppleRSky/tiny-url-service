package cod.nord.repository.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    USER("user"),
    ADMIN("admin"),
//    GUEST("guest")
    ;

    private final String authority;

    @Override
    public String toString() {
        return authority;
    }

}
