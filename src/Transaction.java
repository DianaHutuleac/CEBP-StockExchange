public class Transaction {
    private Offer offer;
    private Request request;
    private int quantity;
    private double pricePerShare;

    public Transaction(Offer offer, Request request, int quantity, double pricePerShare) {
        this.offer = offer;
        this.request = request;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
    }

    public Offer getOffer() {
        return offer;
    }

    public Request getRequest() {
        return request;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    @Override
    public String toString() {
        String formattedPrice = String.format("%.2f", pricePerShare);
        return "Transaction: " + quantity + " shares of " + offer.getShare().getCompanyName() +
                " sold from " + offer.getSellerId() + " to " + request.getBuyerId() +
                " at " + formattedPrice + " per share.";
    }
}
