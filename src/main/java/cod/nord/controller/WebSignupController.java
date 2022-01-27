package cod.nord.controller;

import cod.nord.service.UserService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebSignupController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }


    @PostMapping("/signup")
    public String addUser() {
        throw new NotImplementedException("String addUser()");
    }

}
