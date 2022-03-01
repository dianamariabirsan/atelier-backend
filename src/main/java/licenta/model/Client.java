package licenta.model;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
public class Client extends BaseEntity<Long> implements Serializable {
    private String name;
    private String email;
    private String phoneNumber;

    public Client(Long aLong, String name, String email, String phoneNumber) {
        super(aLong);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Client() {
    }
}
