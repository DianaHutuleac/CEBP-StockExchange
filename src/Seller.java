import java.util.Random;

public class Seller implements Runnable {
    private String sellerId;
    private StockExchange stockExchange;
    private Random random = new Random();

    public Seller(String sellerId, StockExchange stockExchange) {
        this.sellerId = sellerId;
        this.stockExchange = stockExchange;
    }

    @Override
    public void run() {
        while (true) {
            Stock stock = new Stock("Company " + (random.nextInt(3) + 1), 1000);
            int quantity = random.nextInt(10) + 1;
            double price = random.nextDouble() * 100;

            Offer offer = new Offer(stock, quantity, price, sellerId);
            stockExchange.addOffer(offer);

            try {
                Thread.sleep(random.nextInt(3000)); // Random pause between offers
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
