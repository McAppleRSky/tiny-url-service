package cod.nord.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique=true, nullable=false, length=3)
    private String path;

    @Column(unique=true, nullable=false, length=120)
    @Size(min = 5, max = 120)
    private String url;

    private Timestamp expire;

    @ManyToOne
    @JoinColumn(name="oper_id", nullable=false)
    private Oper oper;

}
