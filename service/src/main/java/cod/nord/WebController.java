package cod.nord;

import cod.nord.repository.entity.Oper;
import cod.nord.service.OperService;
import cod.nord.service.auth.AuthService;
import cod.nord.service.auth.model.JwtRequest;
import cod.nord.service.auth.model.JwtResponse;
import cod.nord.service.auth.model.RefreshJwtRequest;
import cod.nord.service.model.OperRequest;
import cod.nord.service.model.OperResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class WebController implements OperServletable, AuthServletable, UrlServletable {

    @Value("${base.host.name}")
    private String defaultHostName;

    private final AuthService authService;
    private final OperService operService;
    private final Map<String, String> urlService;


    @GetMapping(value = "/api/0.0.1/opers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Override
    public Collection<OperResponse> opers() {
//        throw new NotImplementedException("users /api/0.0.1/user");
        return  operService.findAll();
//        ResponseEntity.ok(users)
    }

    @GetMapping(value = "/api/0.0.1/oper/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public OperResponse oper(@PathVariable Integer id) {
        throw new NotImplementedException("user /api/0.0.1/user/{id}");
//        return userService.getById(id);
    }

    @PostMapping(value = "/api/0.0.1/oper", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Void> create(@Valid @RequestBody OperRequest requested) {
//        throw new NotImplementedException("create /api/0.0.1/oper");
        final int id = operService.create(requested);
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/0.0.1/user/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping(value = "/api/0.0.1/oper/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Oper> update(@PathVariable Integer id, @Valid @RequestBody OperRequest requested) {
//        throw new NotImplementedException("update /api/0.0.1/oper/{id}");
        OperResponse updated = operService.update(id, requested);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-store, no-cache, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Content-Type", "application/json; charset=UTF-8");
//        return operService.update(id, requested);
        return new ResponseEntity(updated, headers, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/0.0.1/oper/{id}")
    @Override
    public void delete(@PathVariable Integer id) {
        operService.delete(id);
//        throw new NotImplementedException("delete /api/0.0.1/oper/{id}");
    }

    @GetMapping("/api/0.0.1/operate")
    @Override
    public String operate(Model model) {
        model.addAttribute(
                "serviceHost",
                "/" );
        model.addAttribute(
                "serviceLoginPath",
                "api/0.0.1/login" );
        return "main";
    }

    @PostMapping("/api/0.0.1/login")
    @Override
    public ResponseEntity<JwtResponse> login(
            //@RequestBody
            JwtRequest authRequest
    ) {
//        throw new NotImplementedException("login /api/0.0.1/login");
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping(
//            path=
                    "/api/0.0.1/token"
//            , consumes = "application/json"
    )
    @Override
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
//        throw new NotImplementedException("token /api/0.0.1/token");
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/api/0.0.1/refresh")
    @Override
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
//        throw new NotImplementedException("refresh /api/0.0.1/refresh");
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @GetMapping
    @Override
    public void pathEmpty(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(404);
        System.out.println();
    }

    @GetMapping("/*")
    @Override
    public void redirect(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String contextPath = request.getContextPath();
        String pathStr = path.split("/", 3)[1];
        if (pathStr.length()<3) {
            String projectUrl = urlService.get(pathStr);
            if (projectUrl==null){
                projectUrl = defaultHostName;
            }}
        response.setHeader("Location", defaultHostName);
        response.setStatus(302);
        // throw bound
    }

}

interface OperServletable {
    Collection<OperResponse>
        opers();
    OperResponse
        oper(Integer id);
    ResponseEntity<Void>
        create(OperRequest requested);
    ResponseEntity<Oper>
        update(Integer id, OperRequest requested);
    void
        delete(Integer id);
}

interface AuthServletable {
    String
        operate(Model model);
    ResponseEntity<JwtResponse>
        login(JwtRequest authRequest);
    ResponseEntity<JwtResponse>
        getNewAccessToken(RefreshJwtRequest request);
    ResponseEntity<JwtResponse>
        getNewRefreshToken(RefreshJwtRequest request);
}

interface UrlServletable {
    void redirect(HttpServletRequest request, HttpServletResponse response);
    void pathEmpty(HttpServletRequest request, HttpServletResponse response);
}
