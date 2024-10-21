import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        StockExchange stockExchange = new StockExchange();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 5; i++) {
            Buyer buyer = new Buyer("Buyer " + i, stockExchange);
            Seller seller = new Seller("Seller " + i, stockExchange);

            executor.submit(buyer);
            executor.submit(seller);
        }

        executor.shutdown();

    }
}