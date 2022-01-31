package cod.nord.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="usr")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User //implements UserDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "username cannot be empty")
    @Column(nullable = false, length=75)
    @Size(min = 3)
    private String name;

    @NotBlank(message = "login cannot be empty")
    @Column(unique=true, nullable = false, length=32)
    @Size(min = 3, max=32)
    private String login;

    // TODO refactor pass encode, match
    @NotBlank(message = "password cannot be empty")
    @Column(nullable = false, length=16)
    @Size(min=8, max=16)
    private String password;

    @Email(message="Email is not correct")
    @NotBlank(message = "email cannot be empty")
    @Column(length=128)
    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(length=8)
    private Set<Role> roles;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
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
