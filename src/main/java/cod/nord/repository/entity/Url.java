package cod.nord.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "url")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "url_path",
            joinColumns =
                    { @JoinColumn(name = "url_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "path_id", referencedColumnName = "id") })
    private Path path;

}
