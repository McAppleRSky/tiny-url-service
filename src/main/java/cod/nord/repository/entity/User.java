package cod.nord.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="user")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User //implements UserDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "username cannot be empty")
    private String name;

    @NotBlank(message = "login cannot be empty")
    private String login;

    @NotBlank(message = "password cannot be empty")
    private String password;

    @Email(message="Email is not correct")
    @NotBlank(message = "email cannot be empty")
    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy="id", cascade=CascadeType.ALL)
//    @OneToMany(cascade = {CascadeType.PERSIST})
    private Set<Direction> directions;

    /* @Override
    public boolean isAccountNonExpired() {
        throw new NotImplementedException("boolean isAccountNonExpired()");}
    @Override
    public boolean isAccountNonLocked() {
        throw new NotImplementedException("boolean isAccountNonLocked()");}
    @Override
    public boolean isCredentialsNonExpired() {
        throw new NotImplementedException("boolean isCredentialsNonExpired()");}
    @Override
    public boolean isEnabled() {
        throw new NotImplementedException("boolean isEnabled()");
    }*/

}
