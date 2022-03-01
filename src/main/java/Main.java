import licenta.model.Client;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello A");
        Client client = new Client(1L, "Pop Alexandra", "pop.alexandra@yahoo.com", "0778987987");
        System.out.println(client);
    }
}
