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

        Iterator<Offer> offerIterator = availableOffers.iterator();
        while (offerIterator.hasNext()) {
            Offer offer = offerIterator.next();

            Iterator<Request> requestIterator = availableRequests.iterator();
            while (requestIterator.hasNext()) {
                Request request = requestIterator.next();

                if (offer.getPricePerShare() <= request.getMaxPricePerShare()) {
                    int transactionQuantity = Math.min(offer.getQuantity(), request.getQuantity());
                    double transactionPrice = offer.getPricePerShare();

                    synchronized (this) {
                        Transaction transaction = new Transaction(offer, request, transactionQuantity, transactionPrice);
                        transactions.add(transaction);

                        offer.setQuantity(offer.getQuantity() - transactionQuantity);
                        request.setQuantity(request.getQuantity() - transactionQuantity);

                        System.out.println(transaction);
                    }



                    if (offer.isFulfilled()) {
                        offerIterator.remove();
                        break;
                    }

                    if (request.isFulfilled()) {
                        requestIterator.remove();
                    }
                }
            }
        }
    }

//    private void matchOffersAndRequests(String companyName) {
//        List<Offer> availableOffers = offers.get(companyName);
//        List<Request> availableRequests = requests.get(companyName);
//
//        if (availableOffers == null || availableRequests == null) return;
//
//        Iterator<Offer> offerIterator = availableOffers.iterator();
//        while (offerIterator.hasNext()) {
//            Offer offer = offerIterator.next();
//
//            Iterator<Request> requestIterator = availableRequests.iterator();
//            while (requestIterator.hasNext()) {
//                Request request = requestIterator.next();
//
//                if (offer.getPricePerShare() <= request.getMaxPricePerShare()) {
//                    int transactionQuantity = Math.min(offer.getQuantity(), request.getQuantity());
//                    double transactionPrice = offer.getPricePerShare();
//
//                    Transaction transaction = new Transaction(offer, request, transactionQuantity, transactionPrice);
//                    transactions.add(transaction);
//
//                    offer.setQuantity(offer.getQuantity() - transactionQuantity);
//                    request.setQuantity(request.getQuantity() - transactionQuantity);
//
//                    System.out.println(transaction);
//
//                    if (offer.isFulfilled()) {
//                        offerIterator.remove();
//                        break;
//                    }
//
//                    if (request.isFulfilled()) {
//                        requestIterator.remove();
//                    }
//                }
//            }
//        }
//    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }
}
