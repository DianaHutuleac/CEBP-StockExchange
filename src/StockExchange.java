import java.util.*;
import java.util.concurrent.*;

public class StockExchange {
    private ConcurrentHashMap<String, List<Offer>> offers = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, List<Request>> requests = new ConcurrentHashMap<>();
    private CopyOnWriteArrayList<Transaction> transactions = new CopyOnWriteArrayList<>();

    public void addOffer(Offer offer) {
        offers.computeIfAbsent(offer.getShare().getCompanyName(), k -> new ArrayList<>()).add(offer);
        matchOffersAndRequests(offer.getShare().getCompanyName());
    }

    public void addRequest(Request request) {
        requests.computeIfAbsent(request.getShare().getCompanyName(), k -> new ArrayList<>()).add(request);
        matchOffersAndRequests(request.getShare().getCompanyName());
    }

    private void matchOffersAndRequests(String companyName) {
        List<Offer> availableOffers = offers.get(companyName);
        List<Request> availableRequests = requests.get(companyName);

        if (availableOffers == null || availableRequests == null) return;

        // Iterate over all offers
        for (Offer offer : availableOffers) {
            // Try to lock each offer separately
            offer.getLock().lock();  // Lock the specific offer, no other seller is affected
            try {
                // Iterate over requests
                for (Request request : availableRequests) {
                    request.getLock().lock();  // Lock the specific request to ensure safety
                    try {
                        if (offer.getPricePerShare() <= request.getMaxPricePerShare()) {
                            int transactionQuantity = Math.min(offer.getQuantity(), request.getQuantity());
                            double transactionPrice = offer.getPricePerShare();

                            // Synchronize only for the transaction addition
                            //synchronized (this) {
                                Transaction transaction = new Transaction(offer, request, transactionQuantity, transactionPrice);
                                transactions.add(transaction);

                                offer.setQuantity(offer.getQuantity() - transactionQuantity);
                                request.setQuantity(request.getQuantity() - transactionQuantity);

                                System.out.println(transaction);
                            //}

                            if (offer.isFulfilled()) {
                                break;  // Move to the next offer if it's fully sold
                            }

                            if (request.isFulfilled()) {
                                availableRequests.remove(request);  // Remove the fulfilled request
                            }
                        }
                    } finally {
                        request.getLock().unlock();  // Unlock the specific request after processing
                    }
                }
            } finally {
                offer.getLock().unlock();  // Unlock the specific offer after processing
            }
        }
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }



}
