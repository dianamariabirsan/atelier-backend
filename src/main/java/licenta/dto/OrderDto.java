package licenta.dto;

import licenta.model.Client;
import licenta.model.Product;
import licenta.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends BaseDto {
    private List<Product> products;
    private Client client;
    private Status status;
}
