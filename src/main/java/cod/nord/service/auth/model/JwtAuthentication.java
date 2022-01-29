package cod.nord.service.auth.model;

import cod.nord.repository.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

// TODO refactoring
@Getter @Setter
public class JwtAuthentication implements Authentication {

    private boolean authenticated;
    private String name;
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public Object getCredentials() {
        throw new NotImplementedException("Object getCredentials()");
    }

    @Override
    public Object getDetails() {
        throw new NotImplementedException("Object getDetails()");
    }

    @Override
    public Object getPrincipal() {
        return name;
    }

    @Override
    public String getName() {
        throw new NotImplementedException("String getName()");
    }

    @Override
    public boolean isAuthenticated() { return authenticated; }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

}
