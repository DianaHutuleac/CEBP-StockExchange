public class Offer {
    private Share share;
    private int quantity;
    private double pricePerShare;
    private String sellerId;

    public Offer(Share share, int quantity, double pricePerShare, String sellerId) {
        this.share = share;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.sellerId = sellerId;
    }

    public Share getShare() {
        return share;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isFulfilled() {
        return quantity <= 0;
    }
}
