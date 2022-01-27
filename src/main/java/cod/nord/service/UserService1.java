package cod.nord.service;

import cod.nord.repository.UserRepository;
import cod.nord.repository.entity.User;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService1 //implements UserDetailsService
{

    @Autowired
    private UserRepository userRepository;

//    @Override
    /*public User loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new NotImplementedException();
//        return userRepository.findByUsername(username);
    }*/

}
