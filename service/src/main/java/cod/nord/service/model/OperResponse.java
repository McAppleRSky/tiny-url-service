package cod.nord.service.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OperResponse {

    private Integer id;
    private String name;

}
