import licenta.model.Client;
import licenta.model.Product;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello A");
        Client client = new Client(1L, "Pop Alexandra", "pop.alexandra@yahoo.com", "0778987987");
        System.out.println(client);
        System.out.println("Product X");
        Product product = new Product(1l, "puzzle", 10);
    }
}
