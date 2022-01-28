package cod.nord.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping//("/")
//@ResponseBody
@Controller
public class MainController {

    private final Map<String, String> mapService;

    @GetMapping(value = "/api/0.0.1/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public void users(HttpServletRequest request, HttpServletResponse response) {
        throw new NotImplementedException("/api/0.0.1/user public void users(), ");
    }

    @GetMapping(value = "/api/0.0.1/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void method1(HttpServletRequest request, HttpServletResponse response) {
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping
    public void redirect(HttpServletRequest request, HttpServletResponse response) {
        String projectUrl = mapService.get(
                request
                        .getPathInfo()
                        .split("/", 3)[1] );
        // null
        // throw bound
        response.setHeader("Location", projectUrl);
        response.setStatus(302);
    }

}
