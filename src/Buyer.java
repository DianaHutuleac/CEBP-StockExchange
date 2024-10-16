import java.util.Random;

public class Buyer implements Runnable {
    private String buyerId;
    private StockExchange stockExchange;
    private Random random = new Random();

    public Buyer(String buyerId, StockExchange stockExchange) {
        this.buyerId = buyerId;
        this.stockExchange = stockExchange;
    }

    @Override
    public void run() {
        while (true) {
            Share share = new Share("Company " + (random.nextInt(3) + 1), 1000);
            int quantity = random.nextInt(10) + 1;
            double maxPrice = random.nextDouble() * 100;

            Request request = new Request(share, quantity, maxPrice, buyerId);
            stockExchange.addRequest(request);

            try {
                Thread.sleep(random.nextInt(3000)); // Random pause between requests
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
