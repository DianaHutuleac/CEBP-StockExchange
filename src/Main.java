import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        Share share = new Share("apple", 5);
        System.out.println(share.getTotalShares());

        Offer offer = new Offer(share, 1, 2.0, "1");
        System.out.println(offer.getPricePerShare());

        Request request = new Request(share, 2, 10.0, "2");
        System.out.println(request.getBuyerId());

        Transaction transaction = new Transaction(offer, request, 3, 8.5);
        System.out.println(transaction.getQuantity());

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