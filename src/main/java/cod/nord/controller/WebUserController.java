package cod.nord.controller;

import cod.nord.repository.entity.User;
import cod.nord.service.UserService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class WebUserController {

    @Autowired
    private UserService userService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String userList(Model model) {
        throw new NotImplementedException("String userList(Model model)");
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String userSave(
            @RequestParam String
                    username,
            @RequestParam Map<String, String>
                    form,
            @RequestParam("userId") User
                    user) {
        throw new NotImplementedException("String userSave(String username, Map<String, String> form, User user)");
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        throw new NotImplementedException("getProfile(Model model, User user)");
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User
                                        user,
                                @RequestParam String
                                        password,
                                @RequestParam String
                                        email) {
        throw new NotImplementedException("String updateProfile(User user, String password, String email");
    }
}
