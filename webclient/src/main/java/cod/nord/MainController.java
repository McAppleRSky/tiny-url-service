package cod.nord;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//@RequiredArgsConstructor
@Controller
public class MainController implements Servletable {

    @GetMapping
    @Override
    public String login(Model model) {
        model.addAttribute(         //
                "serviceHost",      //
                "http://localhost:8080/" );
        model.addAttribute(         //
                "serviceLoginPath", //
                "api/0.0.1/login" );
        return "login";
    }
}

interface Servletable {
    public String login (Model model);
}
