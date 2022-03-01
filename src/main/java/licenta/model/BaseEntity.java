package licenta.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    @GeneratedValue
    private ID id;
}
