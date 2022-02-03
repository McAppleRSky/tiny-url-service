package cod.nord.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Oper //implements UserDetails
{

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    // http://www.h2database.com/html/grammar.html
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(updatable = false, nullable = false, unique = true)
    private Integer id;

    @NotBlank(message = "name cannot be empty")
    @Column(length=75)
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
    @CollectionTable(name = "oper_role", joinColumns = @JoinColumn(name = "oper_id"))
    @Enumerated(EnumType.STRING)
    @Column(length=8)
    private Set<Role> roles;

    @OneToMany(mappedBy="oper", cascade=CascadeType.ALL)
//    @OneToMany(cascade = {CascadeType.PERSIST})
    private Set<Link> directions;

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
