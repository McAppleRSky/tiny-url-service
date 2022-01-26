package cod.nord.repository;

import cod.nord.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {

//    UserDetails loadUserByUsername(String username);
    User findByUsername(String username);
}
