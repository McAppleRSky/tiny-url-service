package cod.nord.controller;

import cod.nord.repository.entity.User;
import cod.nord.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

// /auth/login
 @RestController
 @RequestMapping("/auth")
 @RequiredArgsConstructor
 public class AuthController {

     private final UserService service;

     @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
     public @ResponseBody
     User getAuthUser() {
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         if (auth == null) {
             return null;
         }
         Object principal = auth.getPrincipal();
         User user = (principal instanceof User) ? (User) principal : null;
         return Objects.nonNull(user) ? this.service.getByLogin(user.getUsername()) : null;
     }

 }
