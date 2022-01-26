package cod.nord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

//    @Autowired Service service;

    @PostMapping(value = "/api/0.0.1/tiny")
    public ResponseEntity<String> send(@RequestBody String body){
        int count = 0//dispatcherService.sendMessage(body)
                ;
        return new ResponseEntity<>("Message sent: " + count, count > 0 ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
