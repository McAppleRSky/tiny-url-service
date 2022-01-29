package cod.nord.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
//@Table(name = "direction")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private Integer id;

    @Column(unique=true)
    private String path;

    private String url;
    Timestamp expire;

}
