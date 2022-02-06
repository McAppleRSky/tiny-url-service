package cod.nord.service.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class OperRequest {

    @NotEmpty(message = "{field.is.empty}")
    private String name;
    private String login;
    private String password;
    private String email;

}
