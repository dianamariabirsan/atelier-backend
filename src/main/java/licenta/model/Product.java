package licenta.model;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
public class Product extends BaseEntity<Long> implements Serializable {
    private String type;
    private int price;

    public Product(Long aLong, String type, int price) {
        super(aLong);
        this.type = type;
        this.price = price;
    }

    public Product() {
    }
}
