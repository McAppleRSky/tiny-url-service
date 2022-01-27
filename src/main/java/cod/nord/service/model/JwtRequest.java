package cod.nord.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtRequest {

    private String logname;
    private String password;

}
