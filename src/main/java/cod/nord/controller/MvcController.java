package cod.nord.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class MvcController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView method() {
String projectUrl = null;
        return new ModelAndView("redirect:" + projectUrl);
    }

    /*@RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", projectUrl);
        httpServletResponse.setStatus(302);
    }*/

}
