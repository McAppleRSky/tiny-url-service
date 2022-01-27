package cod.nord.service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(chain = true)
public class UserRequest {

    @NotEmpty(message = "{field.is.empty}")
    private String name;
    private String logname;
    private String password;
    private String email;

}
